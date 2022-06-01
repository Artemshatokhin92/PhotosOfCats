package com.shatokhin.photosofcats.domain.usecase

import com.shatokhin.photosofcats.data.models.Cat
import com.shatokhin.photosofcats.domain.repository.Repository

class UseCaseGetRandomCats(private val repository: Repository) {

    suspend fun execute(): Cat{
        val listCat = repository.getRandomCats(1)
        val cat = listCat[0]
        return cat
    }

}