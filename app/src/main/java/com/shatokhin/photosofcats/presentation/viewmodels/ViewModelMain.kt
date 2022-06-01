package com.shatokhin.photosofcats.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shatokhin.photosofcats.data.models.Cat
import com.shatokhin.photosofcats.domain.models.CatFavorite
import com.shatokhin.photosofcats.domain.usecase.*
import com.shatokhin.photosofcats.utilites.TAG_FOR_LOGCAT
import kotlinx.coroutines.launch

class ViewModelMain(
    private val useCaseGetRandomCats: UseCaseGetRandomCats,
    private val useCaseVoteUpCat: UseCaseVoteUpCat,
    private val useCaseVoteDownCat: UseCaseVoteDownCat,
    private val useCaseGetVotes: UseCaseGetVotes,
    private val useCaseDeleteVote: UseCaseDeleteVote,
    private val useCaseGetCatById: UseCaseGetCatById
) : ViewModel() {

    private val mListCats = MutableLiveData<Cat>()
    val listCats: LiveData<Cat>
        get() = mListCats

    private val mListFavoriteCats = MutableLiveData<List<CatFavorite>>()
    val listFavoriteCats: LiveData<List<CatFavorite>>
        get() = mListFavoriteCats

    fun loadRandomCats() {
        viewModelScope.launch {
            mListCats.value = useCaseGetRandomCats.execute()
        }
    }

    fun voteUpCurrentCat() {
        viewModelScope.launch {
            mListCats.value?.let { currentCat ->
                val response = useCaseVoteUpCat.execute(currentCat)
                if (response.message == "SUCCESS") loadFavoriteCats()
            }
        }
        loadRandomCats()
    }

    fun voteDownCurrentCat() {
//        viewModelScope.launch {
//            mListCats.value?.let { currentCat ->
//                useCaseVoteDownCat.execute(currentCat)
//            }
//        }
        loadRandomCats()
    }

    fun loadFavoriteCats() {
        viewModelScope.launch {
            val listVotes = useCaseGetVotes.execute()

            val listCats = mutableListOf<CatFavorite>()

            listVotes.forEach { vote ->
                if (vote.value == 1) {
                    val cat = useCaseGetCatById.execute(vote.idCat)
                    listCats.add(CatFavorite(cat.id, cat.urlImage, vote.id))
                }
            }

            mListFavoriteCats.value = listCats
        }
    }

    fun deleteFavoriteCat(cat: CatFavorite) {
        viewModelScope.launch {
            val response = useCaseDeleteVote.execute(cat.idVote)
            if (response.message == "SUCCESS") loadFavoriteCats()
        }
    }

}