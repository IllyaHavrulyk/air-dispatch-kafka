package com.havrulyk.listener.processors;

import com.havrulyk.common.messages.AirPortStateMessage;
import com.havrulyk.common.processor.MessageConverter;
import com.havrulyk.common.processor.MessageProcessor;
import com.havrulyk.provider.AirPortsProvider;
import com.havrulyk.provider.BoardsProvider;
import com.havrulyk.service.WaitingRoutesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("AIRPORT_STATE")
@RequiredArgsConstructor
public class AirportProcessor implements MessageProcessor<AirPortStateMessage> {
    private final MessageConverter messageConverter;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final WaitingRoutesService waitingRoutesService;
    private final BoardsProvider boardsProvider;
    private final AirPortsProvider airPortsProvider;

    @Override
    public void process(String jsonMessage) {

    }
}
