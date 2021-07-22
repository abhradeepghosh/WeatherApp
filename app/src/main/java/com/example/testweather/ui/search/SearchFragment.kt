package com.example.testweather.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.*
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.testweather.R
import com.example.testweather.base.BaseFragment
import com.example.testweather.data.entity.Location
import com.example.testweather.ui.WeatherViewModel
import com.example.testweather.ui.weather.WeatherFragment
import com.example.testweather.utils.exception.BadServerResponse
import com.example.testweather.utils.exception.LocationsNotFound
import com.example.testweather.utils.exception.NoConnection
import com.example.testweather.utils.extension.hide
import com.example.testweather.utils.extension.observe
import com.example.testweather.utils.extension.show
import com.example.testweather.utils.extension.snackbarShort
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_hint_enter_text.*
import kotlinx.android.synthetic.main.search_hint_no_internet.*
import kotlinx.android.synthetic.main.search_hint_no_locations.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment() {
    override val layoutId = R.layout.search_fragment
    private val viewModel: SearchViewModel by inject()
    private val activityViewModel: WeatherViewModel by sharedViewModel()
    private val searchAdapter: SearchAdapter by inject {
        parametersOf(::onLocationChange)
    }

    companion object {
        const val TAG = "SearchFragment"
        fun newInstance() = SearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            observe(searchedLocations, ::renderData)
            observe(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setFocusOnInput()
        showHint(enterTextHint)
        searchCityEditText.doOnTextChanged { text, _, _, _ ->
            if (text != null && text.length > 1)
                viewModel.getLocationsByName(text.toString())
        }
    }

    /**
     * Displays found locations
     */
    private fun renderData(locations: List<Location>) {
        if (locations.isEmpty()) {
            handleFailure(LocationsNotFound)
        } else {
            hints.hide()
            locationList.show()
            searchAdapter.submitList(locations)
        }
    }

    /**
     * Shows [hintToShow] and hides previous data with other hints
     */
    private fun showHint(hintToShow: View) {
        locationList.hide()
        hints.show()
        hints.children.forEach {
            if (it != hintToShow)
                it.hide()
            else
                it.show()
        }
    }

    /**
     * Handles with failures that may occur while loading data
     */
    private fun handleFailure(failure: Exception) {
        when (failure) {
            is LocationsNotFound -> showHint(noLocationsHint)
            is NoConnection -> showHint(noInternetHint)
            is BadServerResponse -> snackbarShort(rootLayout, R.string.failure_server_error)
        }
    }

    /**
     * Loads new forecast by given [location], closes keyboard and opens new screen
     */
    private fun onLocationChange(location: Location) {
        activityViewModel.fetchForecast(location)
        hideKeyboard()
        if (isAppFirstLaunched()) {
            showMainScreen()
        } else {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setUpRecyclerView() {
        locationList.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
            adapter = searchAdapter
            setHasFixedSize(true)
        }
    }

    /**
     * Sets focus on [searchCityEditText] and opens keyboard
     */
    private fun setFocusOnInput() {
        searchCityEditText.requestFocus()
        val inputMethodManager =
            requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(SHOW_FORCED, HIDE_IMPLICIT_ONLY)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            searchCityEditText.windowToken,
            RESULT_UNCHANGED_SHOWN
        )
    }

    private fun isAppFirstLaunched(): Boolean = parentFragmentManager.backStackEntryCount == 0

    private fun showMainScreen() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, WeatherFragment.newInstance())
            .commitNow()
    }
}