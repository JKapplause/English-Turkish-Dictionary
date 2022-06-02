package com.info.sozlukuygulamadb

class KelimelerDao {

    fun tumKelimeler(vt:VeritabaniYardimcisi) : ArrayList<Kelimeler> {
        val  kelimelerListe = ArrayList<Kelimeler>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM kelimeler", null)

        while (c.moveToNext()) {
            val kelime = Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
            ,c.getString(c.getColumnIndex("İngilizce"))
            ,c.getString(c.getColumnIndex("turkce")))
        }

        return kelimelerListe
    }

    fun aramaYap(vt:VeritabaniYardimcisi, aramaKelime:String) : ArrayList<Kelimeler> {
        val  kelimelerListe = ArrayList<Kelimeler>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM kelimeler Where İngilizce like '%$aramaKelime'", null)

        while (c.moveToNext()) {
            val kelime = Kelimeler(c.getInt(c.getColumnIndex("kelime_id"))
                ,c.getString(c.getColumnIndex("İngilizce"))
                ,c.getString(c.getColumnIndex("turkce")))
        }

        return kelimelerListe
    }
}