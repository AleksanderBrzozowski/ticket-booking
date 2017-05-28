package com.maly.presentation.play

import com.maly.domain.play.PlayService
import com.maly.presentation.play.PlayController.Companion.BASE_PATH
import com.maly.system.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}/$BASE_PATH")
class PlayController(private val playService: PlayService) {

    companion object {
        const val BASE_PATH = "/plays"
    }

    @GetMapping
    fun getPlays(@RequestParam(required = false) query: String?): List<PlayModel> {
        val plays = (query?.let { playService.getPlaysByQuery(it) }
                ?: playService.getPlays())

        return plays.map { PlayModel.of(it) }
    }
}