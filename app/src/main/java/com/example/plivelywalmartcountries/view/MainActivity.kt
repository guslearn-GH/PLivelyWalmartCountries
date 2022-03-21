package com.example.plivelywalmartcountries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plivelywalmartcountries.databinding.ActivityMainBinding
import com.example.plivelywalmartcountries.di.DI
import com.example.plivelywalmartcountries.model.UIState

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        DI.provideViewModel(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureObserver()
    }

    private fun configureObserver() {
        viewModel.countryLiveData.observe(this) {
            when(it) {
                is UIState.Loading -> {
                    binding.run {
                        progressBar.visibility = View.VISIBLE
                        tvErrorText.visibility = View.GONE
                        rvCountries.visibility = View.GONE
                    }
                }
                is UIState.Error -> {
                    binding.run {
                        progressBar.visibility = View.GONE
                        tvErrorText.text = it.msg
                        tvErrorText.visibility = View.VISIBLE
                        rvCountries.visibility = View.GONE
                    }
                }
                is UIState.Success -> {
                    binding.run {
                        progressBar.visibility = View.GONE
                        tvErrorText.visibility = View.GONE

                        rvCountries.run {
                            adapter = CountryAdapter(it.countryResponses)
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}