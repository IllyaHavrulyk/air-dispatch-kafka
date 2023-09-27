package com.havrulyk.listener.processors;

import java.util.Optional;

import com.havrulyk.provider.BoardsProvider;
import com.havrulyk.service.WaitingRoutesService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.havrulyk.common.bean.AirPort;
import com.havrulyk.common.bean.Board;
import com.havrulyk.common.bean.Route;
import com.havrulyk.common.messages.AirPortStateMessage;
import com.havrulyk.common.messages.BoardStateMessage;
import com.havrulyk.common.processor.MessageConverter;
import com.havrulyk.common.processor.MessageProcessor;
import com.havrulyk.provider.AirPortsProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("BOARD_STATE")
@RequiredArgsConstructor
public class BoardStateProcessor implements MessageProcessor<BoardStateMessage> {

    private final MessageConverter messageConverter;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final WaitingRoutesService waitingRoutesService;
    private final BoardsProvider boardsProvider;
    private final AirPortsProvider airPortsProvider;

    @Override
    public void process(String jsonMessage) {
        BoardStateMessage message = messageConverter.extractMessage(jsonMessage, BoardStateMessage.class);
        Board board = message.getBoard();
        Optional<Board> previousOpt = boardsProvider.getBoard(board.getName());
        AirPort airPort = airPortsProvider.getAirPort(board.getLocation());

        boardsProvider.addBoard(board);
        if(previousOpt.isPresent() && board.hasRoute() && !previousOpt.get().hasRoute()){
            Route route = board.getRoute();
            waitingRoutesService.remove(route);
        }

        if (previousOpt.isEmpty() || !board.isBusy() && previousOpt.get().isBusy()){
            airPort.addBoard(board.getName());
            kafkaTemplate.sendDefault(messageConverter.toJson(new AirPortStateMessage(airPort)));
        }
    }
}
