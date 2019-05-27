package pl.salon24.model.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "ARTICLES")
data class Article(
        @Id
        val id: String,
        val url: String,
        val title: String,
        @OneToOne(cascade = [CascadeType.ALL])
        val author: User,
        @Column(columnDefinition = "TEXT")
        val content: String,
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val tags: Set<Tag>
) {

    override fun equals(other: Any?) =
            if (other is Article) {
                id == other.id
            } else {
                false
            }

    override fun hashCode() = id.hashCode()
}