package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import javax.inject.Inject


data class UpdateProfileBodyDto
@Inject
    constructor(
    val name: String,
    val avatar_url: String,
    val password: String? = null // Campo opcional si es necesario
)
