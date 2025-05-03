package com.android.example.rickandmortygraphql.data

import com.android.example.rickandmortygraphql.model.CharacterStatus
import com.android.example.rickandmortygraphql.model.DetailedCharacter
import com.android.example.rickandmortygraphql.model.Episode
import com.android.example.rickandmortygraphql.model.SimpleCharacter
import com.example.CharacterQuery
import com.example.CharactersQuery
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun CharactersQuery.Characters.toSimpleCharacters(): List<SimpleCharacter> {
    return this.results?.filterNotNull()?.map { character ->
        SimpleCharacter(
            name = character.name,
            status = character.status?.let { status ->
                CharacterStatus.valueOf(status.replaceFirstChar {
                    it.uppercase()
                })
            },
            species = character.species,
            origin = character.origin?.name,
            lastKnownLocation = character.location?.name,
            image = character.image ?: "",
            id = character.id,
        )
    } ?: emptyList()
}

fun CharacterQuery.Character.toDetailedCharacter(): DetailedCharacter {
    return DetailedCharacter(
        id = id,
        image = image,
        status = status?.let { status ->
            CharacterStatus.valueOf(status.replaceFirstChar {
                it.uppercase()
            })
        },
        species = species,
        origin = origin?.name,
        lastKnownLocation = location?.name,
        episodes = episode.map {
            Episode(
                id = it?.id,
                name = it?.name,
                created = formatEpisodeDate(it?.created ?: "")
            )
        },
        name = name,
    )
}

fun formatEpisodeDate(isoDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    val outputFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)

    return try {
        val date: Date = inputFormat.parse(isoDate)!!
        outputFormat.format(date)
    } catch (e: Exception) {
        Timber.e("error while parsing: ${e.message}")
        isoDate
    }
}