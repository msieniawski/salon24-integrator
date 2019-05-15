package pl.salon24.model.repository

import pl.salon24.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>