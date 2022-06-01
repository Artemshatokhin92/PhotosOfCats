package com.shatokhin.photosofcats.data.repository

import android.util.Log
import com.shatokhin.photosofcats.data.models.*
import com.shatokhin.photosofcats.domain.repository.Repository
import com.shatokhin.photosofcats.utilites.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RepositoryImpl: Repository {
    private val clientHttp = HttpClient()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getRandomCats(count: Int): List<Cat> {
        val fullUrl = BASE_URL + URL_RANDOM_CAT + count
        val jsonString = clientHttp.get<String>(fullUrl)
        return json.decodeFromString(jsonString)
    }

    override suspend fun  voteUpCat(cat: Cat): ResultPostVote {
        return voteCat(cat, 1)
    }

    override suspend fun voteDownCat(cat: Cat): ResultPostVote {
        return voteCat(cat, 0)
    }

    private suspend fun voteCat(cat: Cat, vote: Int): ResultPostVote {
        val fullUrl = BASE_URL + VOTE_URL

        val obj = VotePost(cat.id, "0", vote)
        val objJson = json.encodeToString(obj)
        val resultPost = clientHttp.post<String>(fullUrl){
            contentType(ContentType.Application.Json)
            header(NAME_HEADER_AUT, KEY_API)
            body = objJson
        }
        val respons = json.decodeFromString<ResultPostVote>(resultPost)
        return respons
    }

    override suspend fun getVotes(): List<Vote> {
        val fullUrl = BASE_URL + VOTE_URL
        val jsonString = clientHttp.get<String>(fullUrl){
            header(NAME_HEADER_AUT, KEY_API)
        }
        return json.decodeFromString(jsonString)
    }

    override suspend fun deleteVote(idVote: Int): ResultDeleteVote {
        val fullUrl = BASE_URL + VOTE_URL + "/${idVote}"
        Log.d(TAG_FOR_LOGCAT, fullUrl)
        val resultDelete = clientHttp.delete<String>(fullUrl){
            header(NAME_HEADER_AUT, KEY_API)
        }
        val respons = json.decodeFromString<ResultDeleteVote>(resultDelete)
        return respons
    }

    override suspend fun getCatById(idCat: String): Cat { // не тестировалось!!!!!
        val fullUrl = BASE_URL + URL_CAT_BY_ID + idCat
        val jsonString = clientHttp.get<String>(fullUrl){
            header(NAME_HEADER_AUT, KEY_API)
        }
        return json.decodeFromString(jsonString)
    }
}