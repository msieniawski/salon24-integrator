package pl.salon24.model.mapper

import org.springframework.stereotype.Component
import pl.salon24.comments.UserDto
import pl.salon24.model.entity.User

@Component
class UserDtoToEntityMapper : Mapper<UserDto, User> {

    override fun map(from: UserDto) =
            User(
                    id = from.id,
                    url = from.url ?: "",
                    name = from.nick,
                    imageUrl = from.img
            )
}