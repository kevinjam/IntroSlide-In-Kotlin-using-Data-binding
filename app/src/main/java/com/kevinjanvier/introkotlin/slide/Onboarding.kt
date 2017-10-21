package com.kevinjanvier.introkotlin.slide

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView

class Onboarding : AppCompatActivity() {


    private var viewPager: ViewPager? = null
    private var onboardingAdapter: OnboardingAdapter? = null
   var layouts: IntArray? = null
    private var btnNext: Button? = null
    private var prefManager: Constants? = null
    lateinit var indicators: Array<ImageView>
    lateinit var zero: ImageView
    lateinit var one: ImageView
    lateinit var two: ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Checking for first time launch - before calling setContentView()
        prefManager = Constants(this)
        if (!prefManager!!.isFirstTimeLaunch) {
            launchHomeScreen()
            finish()
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

       setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager) as ViewPager
        //        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        //        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next) as Button


        zero = findViewById(R.id.intro_indicator_0) as ImageView
        one = findViewById(R.id.intro_indicator_1) as ImageView
        two = findViewById(R.id.intro_indicator_2) as ImageView

        //        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);
        indicators = arrayOf(zero, one, two)


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = intArrayOf(R.layout.welcome_slide_one, R.layout.welcome_slide_two, R.layout.welcome_slide_tree)


        // adding bottom dots
        //        addBottomDots(0);
        updateIndicators(0)

        // making notification bar transparent
        changeStatusBarColor()

        onboardingAdapter = OnboardingAdapter(this, layouts!!)
        viewPager!!.adapter = onboardingAdapter
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)

        btnNext!!.setOnClickListener {
            // checking for last page
            // if last page home screen will be launched
            val current = getItem(+1)
            if (current < layouts!!.size) {
                // move to next screen
                viewPager!!.currentItem = current
            } else {
                launchHomeScreen()
            }
        }
    }

    internal fun updateIndicators(position: Int) {
        for (i in indicators.indices) {
            indicators[i].setBackgroundResource(
                    if (i == position) R.drawable.indicator_selected else R.drawable.indicator_unselected
            )
        }
    }


    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }

    private fun launchHomeScreen() {
        prefManager!!.isFirstTimeLaunch = false
        startActivity(Intent(this@Onboarding, MainActivity::class.java))
        finish()
    }

    //	viewpager change listener
    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            updateIndicators(position)

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts!!.size - 1) {
                // last page. make button text to GOT IT
                btnNext!!.visibility = View.VISIBLE
            } else {
                // still pages are left
                btnNext!!.visibility = View.GONE

            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }


    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }


    override fun onLowMemory() {
        super.onLowMemory()
    }
}
