package com.example.testweather.ui.settings

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.example.testweather.BuildConfig
import com.example.testweather.R
import com.example.testweather.base.BaseFragment
import com.example.testweather.databinding.SettingsFragmentBinding
import com.example.testweather.ui.WeatherViewModel
import com.example.testweather.utils.Pressure
import com.example.testweather.utils.Temperature
import com.example.testweather.utils.Theme
import com.example.testweather.utils.Wind
import com.example.testweather.utils.extension.openLink
import com.example.testweather.views.SettingsItem
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SettingsFragment : BaseFragment() {
    override val layoutId = R.layout.settings_fragment
    private val viewModel: SettingsViewModel by inject()
    private val activityViewModel: WeatherViewModel by sharedViewModel()

    private lateinit var binding: SettingsFragmentBinding

    companion object {
        const val TAG = "SettingsFragment"
        fun newInstance() = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingsFragmentBinding.bind(view)
        setUpAppearanceSection()
        setUpUnitsSection()
        setUpAboutSection()
    }

    private fun setUpAppearanceSection() {
        binding.themes.setValue(viewModel.theme)
        binding.themes.setOnClickListener {
            showDialog(
                titleId = R.string.theme_title,
                items = viewModel.availableThemes,
                valueToUpdate = binding.themes
            ) { item: Theme ->
                viewModel.updateTheme(item)
            }
        }
    }

    private fun setUpUnitsSection() {
        // Temperature
        binding.temperature.setValue(viewModel.temperature)
        binding.temperature.setOnClickListener {
            showDialog(
                titleId = R.string.temperature_title,
                items = viewModel.availableTemperatureUnits,
                valueToUpdate = binding.temperature
            ) { item: Temperature ->
                viewModel.updateTemperatureUnit(item)
            }
        }

        // Wind
        binding.wind.setValue(viewModel.wind)
        binding.wind.setOnClickListener {
            showDialog(
                titleId = R.string.wind_title,
                items = viewModel.availableWindUnits,
                valueToUpdate = binding.wind
            ) { item: Wind ->
                viewModel.updateWindUnit(item)
            }
        }

        // Pressure
        binding.pressure.setValue(viewModel.pressure)
        binding.pressure.setOnClickListener {
            showDialog(
                titleId = R.string.pressure_title,
                items = viewModel.availablePressureUnits,
                valueToUpdate = binding.pressure
            ) { item: Pressure ->
                viewModel.updatePressureUnit(item)
            }
        }
    }


    private fun setUpAboutSection() {
        github.setOnClickListener { openLink(R.string.settings_summary_github) }
        author.setOnClickListener { openLink(R.string.settings_vk_link) }
        binding.appVersion.setValue(BuildConfig.VERSION_NAME)
        binding.buildNumber.setValue(BuildConfig.VERSION_CODE.toString())
    }

    /**
     * Creates and shows material dialog based on list of elements.
     * @param titleId Title from string resources
     * @param items Array of all available elements to choose from
     * @param valueToUpdate SettingsItem view on which to update chosen value
     * @param onSelect high-order function with item (chosen element) as a parameter
     */
    private inline fun <T> showDialog(
        @StringRes titleId: Int,
        items: Array<T>,
        valueToUpdate: SettingsItem,
        crossinline onSelect: (item: T) -> Unit
    ) {
        MaterialDialog(requireContext()).show {
            title(titleId)
            listItems(items = items.map { it.toString() }) { _, index, text ->
                valueToUpdate.setValue(text.toString())
                onSelect(items[index])
                activityViewModel.applyNewUnits()
            }
        }
    }
}