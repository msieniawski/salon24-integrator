package pl.salon24.model.mapper

interface Mapper<From, To> {

    fun map(from: Iterable<From>): List<To> =
            from.map { map(it) }

    fun map(from: From): To
}