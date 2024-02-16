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
package example.micronaut.advanced.reference

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import example.micronaut.model.ViewModel
import io.micronaut.views.View

@Controller("/reference")
class ReferenceController {

    @View("reference")
    @Get(produces = [MediaType.TEXT_HTML], consumes = [MediaType.TEXT_HTML])
    fun index() = ViewModel("Reference", pageClass = "index")

    @View("turbo-drive")
    @Get(value = "/turbo-drive", produces = [MediaType.TEXT_HTML], consumes = [MediaType.TEXT_HTML])
    fun turboDrive() = ViewModel("Turbo Drive")

    @View("turbo-frames")
    @Get(value = "/turbo-frames", produces = [MediaType.TEXT_HTML], consumes = [MediaType.TEXT_HTML])
    fun turboFrames() = ViewModel("Turbo Frames")

    @View("turbo-streams")
    @Get(value = "/turbo-streams", produces = [MediaType.TEXT_HTML], consumes = [MediaType.TEXT_HTML])
    fun turboStreams() = ViewModel("Turbo Streams")

    @View("turbo-native")
    @Get(value = "/turbo-native", produces = [MediaType.TEXT_HTML], consumes = [MediaType.TEXT_HTML])
    fun turboNative() = ViewModel("Turbo Native")
}