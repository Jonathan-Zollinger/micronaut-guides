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

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class BookService {

    private static final List<Book> bookStore = new ArrayList<>();

    @PostConstruct
    void init() {
        bookStore.add(new Book("1491950358", "Building Microservices"));
        bookStore.add(new Book("1680502395", "Release It!"));
        bookStore.add(new Book("0321601912", "Continuous Delivery"));
    }

    public List<Book> listAll() {
        return bookStore;
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookStore.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst();
    }
}
