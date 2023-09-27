package com.havrulyk.listener.processors;

import com.havrulyk.common.bean.Route;
import com.havrulyk.common.messages.OfficeRouteMessage;
import com.havrulyk.common.processor.MessageConverter;
import com.havrulyk.common.processor.MessageProcessor;
import com.havrulyk.provider.BoardsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("OFFICE_ROUTE")
@RequiredArgsConstructor
public class OfficeRouteProcessor implements MessageProcessor<OfficeRouteMessage> {

    private final BoardsProvider boardProvider;
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
