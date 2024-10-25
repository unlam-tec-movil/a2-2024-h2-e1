package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import javax.inject.Inject

data class RegisterBodyDto
    @Inject
    constructor(
        val email: String,
        val password: String,
        val name: String,
        val avatar_url: String = "https://images.pexels.com/photos/428364/pexels-photo-428364.jpeg",
    )
