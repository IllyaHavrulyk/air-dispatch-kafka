package com.havrulyk.common.processor;

import com.havrulyk.common.messages.Message;

public interface MessageProcessor<T extends Message> {

    void process(String jsonMessage);

}
