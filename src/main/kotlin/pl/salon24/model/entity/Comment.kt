package pl.salon24.model.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "COMMENTS")
data class Comment(
        @Id
        val id: String,
        @OneToOne(cascade = [CascadeType.ALL])
        val author: User,
        val created: String,
        @Column(columnDefinition = "TEXT")
        val content: String,
        val format: String,
        val replies: Int,
        val likes: Int,
        val dislikes: Int,
        val votes: Int,
        val hidden: Boolean,
        val deleted: String,
        @OneToMany(cascade = [CascadeType.ALL])
        val comments: Set<Comment>
)