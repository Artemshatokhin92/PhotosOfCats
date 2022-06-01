package com.shatokhin.photosofcats.domain.repository

import com.shatokhin.photosofcats.data.models.Cat
import com.shatokhin.photosofcats.data.models.ResultDeleteVote
import com.shatokhin.photosofcats.data.models.ResultPostVote
import com.shatokhin.photosofcats.data.models.Vote

interface Repository {
    suspend fun getRandomCats(count: Int): List<Cat>
    suspend fun voteUpCat(cat: Cat): ResultPostVote
    suspend fun voteDownCat(cat: Cat): ResultPostVote
    suspend fun getVotes(): List<Vote>
    suspend fun deleteVote(idVote: Int): ResultDeleteVote
    suspend fun getCatById(idCat: String): Cat
}