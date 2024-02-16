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
package example.micronaut

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.StreamingHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import reactor.core.publisher.Flux

@MicronautTest
class BookControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: StreamingHttpClient

    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    @Test
    fun testRetrieveBooks() {
        val books = Flux
                .from(client.jsonStream(HttpRequest.GET<Any>("/books"), BookRecommendation::class.java))
                .collectList()
                .block()
        assertEquals(1, books.size)
        assertEquals("Building Microservices", books[0].name)
    }
}
