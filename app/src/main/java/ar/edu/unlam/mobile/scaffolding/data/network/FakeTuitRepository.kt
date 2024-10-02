package ar.edu.unlam.mobile.scaffolding.data.network

import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeTuitRepository
    @Inject
    constructor() : TuitRepository {
        private val tuits = mutableListOf<Tuit>()

        init {
            val newTuits =
                listOf(
                    Tuit(
                        id = 1,
                        authorName = "John Doe",
                        content = "Esto es un tuit de prueba!",
                        avatar = "https://ih1.redbubble.net/image.1221593566.8336/mwo,x1000,ipad_2_snap-pad,750x1000,f8f8f8.jpg",
                        likes = 0,
                        liked = false,
                        replies = 0,
                        reply = { id -> },
                    ),
                    Tuit(
                        id = 2,
                        authorName = "Jane Doe",
                        content = "Otro tuit de prueba!",
                        avatar = "https://ih1.redbubble.net/image.1221593566.8336/mwo,x1000,ipad_2_snap-pad,750x1000,f8f8f8.jpg",
                        likes = 0,
                        liked = false,
                        replies = 0,
                        reply = { id -> },
                    ),
                )

            tuits.addAll(newTuits)
        }

        override fun getTuits(): Flow<List<Tuit>> =
            flow {
                delay(1000L)
                emit(tuits)
                delay(5000L)
                throw Exception("Error al obtener los tuits")
            }

        override fun createTuit(tuit: Tuit) {
            tuits.add(tuit)
        }
    }
