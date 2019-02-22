package me.mfathy.mobiquity.data.mapper


/**
 * Mapper contract to convert and map data entities.
 */
interface EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D
}