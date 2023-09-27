package com.havrulyk.listener.processors;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.havrulyk.common.messages.AirPortStateMessage;
import com.havrulyk.common.messages.OfficeStateMessage;
import com.havrulyk.common.processor.MessageConverter;
import com.havrulyk.common.processor.MessageProcessor;
import com.havrulyk.provider.AirPortsProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("OFFICE_STATE")
@RequiredArgsConstructor
public class OfficeStateProcessor implements MessageProcessor<OfficeStateMessage> {

    private final MessageConverter messageConverter;
    private final AirPortsProvider airPortsProvider;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void process(String jsonMessage) {
        airPortsProvider.getPorts().forEach(airPort -> {
            kafkaTemplate.sendDefault(messageConverter.toJson(new AirPortStateMessage(airPort)));
        });
    }
}
