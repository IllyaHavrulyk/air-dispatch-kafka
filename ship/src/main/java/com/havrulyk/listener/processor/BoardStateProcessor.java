package com.havrulyk.listener.processor;

import org.springframework.stereotype.Component;

import com.havrulyk.common.messages.BoardStateMessage;
import com.havrulyk.common.processor.MessageProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("BOARD_STATE")
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {

    @Override
    public void process(String jsonMessage) {
        //IGNORE
    }
}
