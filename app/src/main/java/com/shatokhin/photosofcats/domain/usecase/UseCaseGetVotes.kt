package com.shatokhin.photosofcats.domain.usecase

import com.shatokhin.photosofcats.data.models.Vote
import com.shatokhin.photosofcats.domain.repository.Repository

class UseCaseGetVotes(private val repository: Repository) {

    suspend fun execute(): List<Vote> {
        return repository.getVotes()
    }

}