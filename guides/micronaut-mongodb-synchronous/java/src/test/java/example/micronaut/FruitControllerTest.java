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

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.micronaut.http.HttpStatus.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@MicronautTest
public class FruitControllerTest {

    @Inject
    FruitClient fruitClient;

    @Test
    void fruitsEndpointInteractsWithMongo() {

        List<Fruit> fruits = fruitClient.findAll();
        assertTrue(fruits.isEmpty());

        HttpStatus status = fruitClient.save(new Fruit("banana"));
        assertEquals(CREATED, status);

        fruits = fruitClient.findAll();
        assertFalse(fruits.isEmpty());
        assertEquals("banana", fruits.get(0).getName());
        assertNull(fruits.get(0).getDescription());

        status = fruitClient.save(new Fruit("Apple", "Keeps the doctor away"));
        assertEquals(CREATED, status);

        fruits = fruitClient.findAll();
        assertTrue(fruits.stream().anyMatch(f -> "Keeps the doctor away".equals(f.getDescription())));
    }
}
