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
import groovy.transform.EqualsAndHashCode
import io.micronaut.core.annotation.Creator
import io.micronaut.core.annotation.NonNull
import io.micronaut.serde.annotation.Serdeable

import jakarta.validation.constraints.NotBlank

@CompileStatic
@EqualsAndHashCode
@Serdeable
class Book {

    @NonNull
    @NotBlank
    final String isbn

    @NonNull
    @NotBlank
    final String name

    @Creator
    Book(@NonNull @NotBlank String isbn,
         @NonNull @NotBlank String name) {
        this.isbn = isbn
        this.name = name
    }
}
