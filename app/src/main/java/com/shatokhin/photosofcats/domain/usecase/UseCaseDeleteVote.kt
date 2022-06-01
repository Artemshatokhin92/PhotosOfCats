package com.shatokhin.photosofcats.domain.usecase

import com.shatokhin.photosofcats.data.models.ResultDeleteVote
import com.shatokhin.photosofcats.domain.repository.Repository

class UseCaseDeleteVote(private val repository: Repository) {

    suspend fun execute(idVote: Int): ResultDeleteVote {
        return repository.deleteVote(idVote)
    }

}