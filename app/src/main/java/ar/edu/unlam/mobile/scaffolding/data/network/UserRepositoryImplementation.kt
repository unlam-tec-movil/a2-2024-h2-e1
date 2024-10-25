package ar.edu.unlam.mobile.scaffolding.data.network

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImplementation
    @Inject
    constructor() : UserRepository {
        private val users = mutableListOf<User>()

        init {
            users.addAll(
                listOf(
                    User(
                        1u,
                        "https://images.pexels.com/photos/428364/pexels-photo-428364.jpeg",
                        "notengo@idea.com",
                        "Juan Manuel",
                        "123456",
                    ),
                    User(
                        2u,
                        "https://images.pexels.com/photos/20094341/pexels-photo-20094341.jpeg",
                        "xeneiseNegrillo_laboral@mail.com",
                        "Carlos Negrillo",
                        "123456",
                    ),
                    User(
                        3u,
                        "https://images.pexels.com/photos/1520760/pexels-photo-1520760.jpeg",
                        "latanamari@mail.com",
                        "Mariela Fornatti",
                        "123456",
                    ),
                ),
            )
        }

        override fun logOutUser(user: User) {
            TODO("Not yet implemented")
        }

        override fun logInUser(
            email: String,
            password: String,
        ): User? = this.users.find { it.email == email && it.password == password }

        override fun createUser(user: User) {
            TODO("Not yet implemented")
        }
    }
