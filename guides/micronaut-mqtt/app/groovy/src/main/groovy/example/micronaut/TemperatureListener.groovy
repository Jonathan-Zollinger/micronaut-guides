package example.micronaut

import io.micronaut.core.annotation.Nullable
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.mqtt.annotation.MqttSubscriber
import io.micronaut.mqtt.annotation.Topic

import static java.nio.charset.StandardCharsets.UTF_8

@Slf4j
@CompileStatic
@MqttSubscriber // <1>
class TemperatureListener {

    @Nullable
    BigDecimal temperature = null;

    @Topic('house/livingroom/temperature') // <2>
    void receive(byte[] data) {
        temperature = new BigDecimal(new String(data, UTF_8))
        log.info('temperature: {}', temperature)
    }
}
