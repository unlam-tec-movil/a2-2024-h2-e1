package ar.edu.unlam.mobile.scaffolding.data.network.api.dto

import javax.inject.Inject

data class LoginBodyDto
    @Inject
    constructor(
        val email: String,
        val password: String,
    )
