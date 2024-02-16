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
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured

import java.security.Principal

import static io.micronaut.http.MediaType.TEXT_PLAIN
import static io.micronaut.security.rules.SecurityRule.IS_AUTHENTICATED

@CompileStatic
@Controller('/user') // <1>
class UserController {

    @Secured(IS_AUTHENTICATED) // <2>
    @Produces(TEXT_PLAIN) // <3>
    @Get // <4>
    String index(Principal principal) { // <5>
        return principal.name
    }
}
