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
package example.micronaut.controllers;

import example.micronaut.repositories.RoomRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.views.View;
import io.micronaut.views.turbo.TurboFrameView;

@Controller("/rooms")
public class RoomsControllerEdit extends RoomsController {

    public RoomsControllerEdit(RoomRepository roomRepository) {
        super(roomRepository);
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @View("/rooms/edit")
    @Get("/{id}/edit")
    @Produces(MediaType.TEXT_HTML)
    @TurboFrameView("/rooms/_edit") // <1>
    HttpResponse<?> edit(@PathVariable Long id) {
        return modelResponse(id);
    }
}
