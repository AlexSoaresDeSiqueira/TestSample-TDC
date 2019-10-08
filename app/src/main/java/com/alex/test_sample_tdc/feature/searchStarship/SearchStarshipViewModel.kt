package com.alex.test_sample_tdc.feature.searchStarship

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.test_sample_tdc.data.model.Starship
import com.alex.test_sample_tdc.data.repository.StarshipRepositoryContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchStarshipViewModel(
    private val starshipRepositoryContract: StarshipRepositoryContract,
    private val searchStarshipReducerContract: SearchStarshipReducerContract
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _state = MutableLiveData<SearchStarshipState>()
    val states: LiveData<SearchStarshipState>
        get() = _state

    init {
        _state.postValue(SearchStarshipState())
    }

    fun searchStarship(starshipName: String) {
        starshipRepositoryContract
            .searchStarshipByName(starshipName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                postLoadingValue()
            }
            .toObservable()
            .map { starshipResult ->
                postSuccessValue(starshipResult.results[0])
            }
            .subscribe()
            .apply {
                disposables.add(this)
            }
    }

    private fun postSuccessValue(starship: Starship) {
        val starshipState = searchStarshipReducerContract.reducer(
            _state.value,
            SearchStarshipStates.Success(starship)
        )
        _state.postValue(starshipState)
    }

    private fun postLoadingValue() {
        val starshipState = searchStarshipReducerContract.reducer(
            _state.value,
            SearchStarshipStates.Loading
        )
        _state.postValue(starshipState)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}