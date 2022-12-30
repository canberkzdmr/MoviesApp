package com.canberkzdmr.moviesapplication.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

/**
 * Created on 12/25/2022.
 */
class MovieFragmentFactory @Inject constructor(): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            MainFragment::class.java.name -> MainFragment()
            MovieDetailFragment::class.java.name -> MovieDetailFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}