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

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micrometer.core.annotation.Timed
import io.micrometer.core.annotation.Counted

import java.util.Optional

@Controller("/books") // <1>
@ExecuteOn(TaskExecutors.BLOCKING) // <2>
open class BookController(private val bookRepository: BookRepository) { // <3>

    @Get// <4>
    @Timed("books.index") // <5>
    open fun index(): Iterable<Book> = bookRepository.findAll()

    @Get("/{isbn}") // <6>
    @Counted("books.find") // <7>
    open fun findBook(isbn: String): Optional<Book> = bookRepository.findByIsbn(isbn)
}
