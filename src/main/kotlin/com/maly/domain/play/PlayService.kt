package com.maly.domain.play

import com.maly.domain.play.PlaySpecification.Companion.forBuilding
import com.maly.domain.play.PlaySpecification.Companion.forDistinct
import com.maly.domain.play.PlaySpecification.Companion.forFutureDate
import com.maly.domain.play.PlaySpecification.Companion.forTitle
import org.springframework.data.jpa.domain.Specifications
import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */
@Service
class PlayService(private val playRepository: PlayRepository) {

    fun getPlaysByBuildingId(buildingId: Long): List<Play> {
        val specifications = Specifications.where(forBuilding(buildingId = buildingId))
                .and(forFutureDate())
                .and(forDistinct())

        return playRepository.findAll(specifications)
    }

    fun getPlaysByQuery(query: String): List<Play> {
        val specifications = Specifications.where(forFutureDate())
                .and(forTitle(query))
                .and(forDistinct())
        return playRepository.findAll(specifications)
    }

    fun getPlays(): List<Play> {
        val specifications = Specifications.where(forFutureDate())
                .and(forDistinct())
        return playRepository.findAll(specifications)
    }
}