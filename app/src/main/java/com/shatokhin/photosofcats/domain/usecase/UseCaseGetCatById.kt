package com.shatokhin.photosofcats.domain.usecase

import com.shatokhin.photosofcats.data.models.Cat
import com.shatokhin.photosofcats.domain.repository.Repository

class UseCaseGetCatById(private val repository: Repository) {

    suspend fun execute(idCat: String): Cat {
        return repository.getCatById(idCat)
    }
}