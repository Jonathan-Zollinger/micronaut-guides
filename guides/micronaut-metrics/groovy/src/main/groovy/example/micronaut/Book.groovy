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

import groovy.transform.CompileStatic
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

import static io.micronaut.data.annotation.GeneratedValue.Type.AUTO

@CompileStatic
@MappedEntity // <1>
@Serdeable
class Book {

    @Id // <2>
    @GeneratedValue(AUTO) // <3>
    Long id

    String name
    String isbn

    Book(String isbn, String name) {
        this.isbn = isbn
        this.name = name
    }
}
