package com.shatokhin.photosofcats.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shatokhin.photosofcats.data.repository.RepositoryImpl
import com.shatokhin.photosofcats.domain.usecase.*

class ViewModelMainFactory: ViewModelProvider.Factory {
    private val repositoryImpl = RepositoryImpl()
    private val useCaseGetRandomCats = UseCaseGetRandomCats(repositoryImpl)
    private val useCaseVoteUpCat = UseCaseVoteUpCat(repositoryImpl)
    private val useCaseVoteDownCat = UseCaseVoteDownCat(repositoryImpl)
    private val useCaseGetVotes = UseCaseGetVotes(repositoryImpl)
    private val useCaseDeleteVote = UseCaseDeleteVote(repositoryImpl)
    private val useCaseGetCatById = UseCaseGetCatById(repositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelMain(
            useCaseDeleteVote = useCaseDeleteVote,
            useCaseGetRandomCats = useCaseGetRandomCats,
            useCaseGetVotes = useCaseGetVotes,
            useCaseVoteDownCat = useCaseVoteDownCat,
            useCaseVoteUpCat = useCaseVoteUpCat,
            useCaseGetCatById = useCaseGetCatById
        ) as T
    }

}