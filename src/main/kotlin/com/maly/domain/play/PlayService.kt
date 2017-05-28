package com.maly.domain.play

import org.springframework.data.jpa.domain.Specifications
import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */
@Service
class PlayService(private val playRepository: PlayRepository) {

    fun getPlaysByBuildingId(buildingId: Long): List<Play> {
        val specifications = Specifications.where(
                PlaySpecification.forBuilding(buildingId = buildingId, distinctPlays = true)
        )

        return playRepository.findAll(specifications)
    }

    fun getPlaysByQuery(query: String) = playRepository.findAllByNameIgnoreCaseStartsWith(query)

    fun getPlays(): List<Play> = playRepository.findAll()

}