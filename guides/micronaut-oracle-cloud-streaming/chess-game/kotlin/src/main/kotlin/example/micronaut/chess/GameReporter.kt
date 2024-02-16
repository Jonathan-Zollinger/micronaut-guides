/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.micronaut.chess

import example.micronaut.chess.dto.GameDTO
import example.micronaut.chess.dto.GameStateDTO
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.core.annotation.NonNull
import reactor.core.publisher.Mono

@KafkaClient // <1>
interface GameReporter {

    @Topic("chessGame") // <2>
    @NonNull
    fun game(@NonNull @KafkaKey gameId: String, // <4>
             @NonNull game: GameDTO): Mono<GameDTO> // <3>

    @Topic("chessGameState") // <2>
    @NonNull
    fun gameState(@NonNull @KafkaKey gameId: String, // <4>
                  @NonNull gameState: GameStateDTO): Mono<GameStateDTO> // <3>
}
