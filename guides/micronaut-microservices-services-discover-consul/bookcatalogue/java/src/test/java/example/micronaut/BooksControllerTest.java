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
package example.micronaut;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest // <1>
public class BooksControllerTest {

    @Inject
    @Client("/")
    HttpClient client; // <2>

    @Test
    public void testRetrieveBooks() {
        HttpRequest<?> request = HttpRequest.GET("/books"); // <3>
        List<Book> books = client.toBlocking().retrieve(request, Argument.listOf(Book.class)); // <4>

        assertEquals(3, books.size());
        assertTrue(books.contains(new Book("1491950358", "Building Microservices")));
        assertTrue(books.contains(new Book("1680502395", "Release It!")));
    }
}
