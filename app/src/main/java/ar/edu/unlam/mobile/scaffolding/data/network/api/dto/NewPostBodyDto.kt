package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import javax.inject.Inject

data class NewPostBodyDto
    @Inject
    constructor(
        val message: String,
    )
