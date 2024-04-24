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

import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import jakarta.inject.Singleton;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;

@Singleton // <1>
public class SqsClientCreatedEventListener implements BeanCreatedEventListener<SqsClient> { // <2>
    private static final String QUEUE_NAME = "demo_queue";
    @Override
    public SqsClient onCreated(BeanCreatedEvent<SqsClient> event) {
        SqsClient client = event.getBean();
        if (client.listQueues().queueUrls().stream().noneMatch(it -> it.contains(QUEUE_NAME))) {
            client.createQueue(
                CreateQueueRequest.builder()
                    .queueName(QUEUE_NAME)
                    .build()
            );
        }
        return client;
    }
}
