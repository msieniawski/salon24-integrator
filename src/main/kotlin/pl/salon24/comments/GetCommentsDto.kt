package pl.salon24.comments

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GetCommentsDto(
        val error: String?,
        @JsonProperty("error_desc")
        val errorDesc: String?,
        val data: CommentsDataDto
)

data class CommentsDataDto(
        val sources: List<*>,
        val comments: CommentsDto,
        val users: UsersDto
) {

    @JsonCreator
    constructor(sources: List<*>, comments: CommentsDto, users: Any) :
            this(sources, comments, if (users is UsersDto) users else UsersDto())
}

data class CommentsDto(
        val sourceId: String,
        val sort: String,
        val last: String,
        val nextUrl: String,
        val selected: Int,
        val data: List<CommentDto>,
        val parentId: String?,
        val prevUrl: String?
)

data class CommentDto(
        val id: String,
        val userId: String,
        val created: String,
        val content: String,
        val format: String,
        val replies: Int,
        val likes: Int,
        val dislikes: Int,
        val votes: Int,
        val hidden: Boolean,
        val deleted: String,
        val comments: CommentsDto?
)

class UsersDto : HashMap<String, UserDto>()

data class UserDto(
        val id: String,
        val nick: String,
        val url: String,
        val img: String
)
