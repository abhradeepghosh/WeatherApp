package com.example.testweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import com.example.testweather.R
import com.example.testweather.base.BaseFragment
import com.example.testweather.ui.search.SearchFragment
import com.example.testweather.ui.weather.WeatherFragment
import com.example.testweather.utils.extension.observe
import org.koin.android.ext.android.inject

class WeatherActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null)
            observe(viewModel.isAppFirstLaunched, ::showNextScreen)
    }

    /**
     * If app is launched for the first time, then navigates to [SearchFragment] to pick the location.
     *
     * If not - opens [WeatherFragment].
     */
    private fun showNextScreen(isAppFirstLaunched: Boolean) {
        val fragment: BaseFragment = if (isAppFirstLaunched) {
            SearchFragment.newInstance()
        } else {
            WeatherFragment.newInstance()
        }
        supportFragmentManager.commitNow {
            replace(R.id.mainContainer, fragment)
        }
    }
}