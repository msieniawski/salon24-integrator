package pl.salon24.model.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "TAGS")
data class Tag(
        @Id
        val id: String,
        val tag: String,
        @ManyToMany(fetch = FetchType.EAGER)
        val articles: MutableSet<Article>
) {

    override fun equals(other: Any?) =
            if (other is Tag) {
                id == other.id
            } else {
                false
            }

    override fun hashCode() = id.hashCode()
}