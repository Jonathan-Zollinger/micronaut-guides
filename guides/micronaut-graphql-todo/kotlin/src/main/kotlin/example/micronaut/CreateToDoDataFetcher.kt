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

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

@Singleton // <1>
open class CreateToDoDataFetcher(
    private val toDoRepository: ToDoRepository,  // <2>
    private val authorRepository: AuthorRepository
) : DataFetcher<ToDo> {

    @Transactional
    override fun get(env: DataFetchingEnvironment): ToDo {
        val title = env.getArgument<String>("title")
        val username = env.getArgument<String>("author")
        val author = authorRepository.findOrCreate(username) // <3>
        val toDo = ToDo(title, author.id!!)
        return toDoRepository.save(toDo) // <4>
    }
}