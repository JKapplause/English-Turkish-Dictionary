package com.info.sozlukuygulamadb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_home.*


class MainActivity : AppCompatActivity() {



    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

   private fun setOnboardingItems() {
       onboardingItemsAdapter = OnboardingItemsAdapter(
           listOf(
               OnboardingItem(
                   onboardingImage = R.drawable.undraw_book_lover_mkck,
                   title = "En İyi Sözlük Deneyimine Başlayın",
                   description = "Artık karmaşıklığa son!"
               ),
               OnboardingItem(
                   onboardingImage = R.drawable.undraw_pic4,
                   title = "Kelimeleri Kolayca Ara, Bul !",
                   description = "Kelime zenginliği ile aradığınız her kelime elinizin altında!"
               ),
               OnboardingItem(
                   onboardingImage = R.drawable.undraw_working_late_pukg,
                   title = "Kolay Kullanım Arayüzü!",
                   description = "Basit bir şekilde uygulamada gezin"
               )
           )
       )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object  :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
            })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            }else {
                navigateToHomeActivity()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToHomeActivity()
        }
        findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener {
            navigateToHomeActivity()
        }
    }

    private  fun navigateToHomeActivity() {
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.indicator_inactive_background
                )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }

    }
    private  fun setCurrentIndicator(position: Int) {

        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}