package com.maly.domain.play

import org.springframework.data.jpa.domain.Specifications
import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */
@Service
class PlayService(private val playRepository: PlayRepository) {

    fun getPlaysByBuildingId(buildingId: Long): List<Play> {
        return playRepository.findAll(Specifications.where(PlaySpecification.forBuilding(buildingId)))
    }

    fun getPlaysByQuery(query: String) = playRepository.findAllByNameIgnoreCaseStartsWith(query)

    fun getPlays(): List<Play> = playRepository.findAll()

}