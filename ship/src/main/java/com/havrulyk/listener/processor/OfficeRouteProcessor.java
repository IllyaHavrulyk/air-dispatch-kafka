package com.havrulyk.listener.processor;

import com.havrulyk.provider.BoardProvider;
import org.springframework.stereotype.Component;

import com.havrulyk.common.bean.Route;
import com.havrulyk.common.messages.OfficeRouteMessage;
import com.havrulyk.common.processor.MessageConverter;
import com.havrulyk.common.processor.MessageProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("OFFICE_ROUTE")
@RequiredArgsConstructor
public class OfficeRouteProcessor implements MessageProcessor<OfficeRouteMessage> {

    private final BoardProvider boardProvider;
    private final MessageConverter messageConverter;

    @Override
    public void process(String jsonMessage) {
        OfficeRouteMessage msg = messageConverter.extractMessage(jsonMessage, OfficeRouteMessage.class);
        Route route = msg.getRoute();
        boardProvider.getBoards().stream()
                .filter(board -> board.noBusy() && route.getBoardName().equals(board.getName()))
                .findFirst()
                .ifPresent(board -> {
                    board.setRoute(route);
                    board.setBusy(true);
                    board.setLocation(null);
                });
    }
}
