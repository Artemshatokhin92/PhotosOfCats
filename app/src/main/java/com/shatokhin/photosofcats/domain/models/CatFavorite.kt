package com.shatokhin.photosofcats.domain.models

import kotlinx.serialization.SerialName

data class CatFavorite (
    val id: String,
    val urlImage: String,
    val idVote: Int
)