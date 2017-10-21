package com.kevinjanvier.introkotlin.slide

/**
 * Created by kevinjanvier on 20/10/2017.
 */

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * View pager adapter
 */
class OnboardingAdapter(val mcontext: Context, val layouts: IntArray) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = layoutInflater!!.inflate(layouts[position], container, false)
        container.addView(view)

        return view
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }


    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val view = obj as View
        container.removeView(view)
    }
}