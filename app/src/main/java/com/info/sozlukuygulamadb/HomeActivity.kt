package com.info.sozlukuygulamadb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_home.*
import java.lang.Exception

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private lateinit var kelimelerListe:ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        veritabaniKopyala()

        toolbar.title = "Sozluk Uygulamasi"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        vt = VeritabaniYardimcisi(this)

        kelimelerListe = KelimelerDao().tumKelimeler(vt)

        kelimelerListe = ArrayList()

        adapter = KelimelerAdapter(this,kelimelerListe)

        rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbarmenu, menu)

        val item = menu?.findItem(R.id.actionAra)
        val SearchView = item?.actionView as SearchView
        SearchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e("Gonderilen arama", query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("Harf girdikce", newText!!)
        return true
    }

    fun veritabaniKopyala() {
        val copyHelper = DatabaseCopyHelper(this)

        try {

            copyHelper.createDataBase()
            copyHelper.openDataBase()

        }catch (e:Exception) {
            e.printStackTrace()
        }
    }
}