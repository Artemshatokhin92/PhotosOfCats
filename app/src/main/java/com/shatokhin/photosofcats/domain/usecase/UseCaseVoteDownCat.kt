package com.shatokhin.photosofcats.domain.usecase

import com.shatokhin.photosofcats.data.models.Cat
import com.shatokhin.photosofcats.data.models.ResultPostVote
import com.shatokhin.photosofcats.domain.repository.Repository

class UseCaseVoteDownCat(private val repository: Repository) {

    suspend fun execute(cat: Cat): ResultPostVote {
        return repository.voteDownCat(cat)
    }

}