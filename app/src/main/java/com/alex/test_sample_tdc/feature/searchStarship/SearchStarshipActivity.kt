package com.alex.test_sample_tdc.feature.searchStarship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.alex.test_sample_tdc.R
import com.alex.test_sample_tdc.feature.searchStarship.model.StarshipView
import kotlinx.android.synthetic.main.activity_starship.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchStarshipActivity : AppCompatActivity() {

    private val loginViewModel: SearchStarshipViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starship)

        observerViewModel()
        searchButtonClick()
    }

    private fun fillValues(starshipView: StarshipView) {
        textViewName.text = starshipView.name
        textViewModel.text = starshipView.model
        textViewStarshipClass.text = starshipView.starshipClass
    }

    private fun observerViewModel() {
        loginViewModel.states.observe(this, Observer {
            it?.let {
                with(it) {
                    when {
                        isLoading -> {}
                        starship != StarshipView() -> fillValues(starship)
                    }
                }
            }
        })
    }

    private fun searchButtonClick() {
        buttonSearch.setOnClickListener {
            loginViewModel.searchStarship(editTextNave.text.toString())
        }
    }
}
