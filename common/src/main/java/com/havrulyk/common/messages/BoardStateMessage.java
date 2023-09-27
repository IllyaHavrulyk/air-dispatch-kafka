package com.havrulyk.common.messages;

import com.havrulyk.common.bean.Board;
import com.havrulyk.common.bean.Source;
import com.havrulyk.common.bean.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardStateMessage extends Message {

    private Board board;

    public BoardStateMessage() {
        this.source = Source.BOARD;
        this.type = Type.STATE;
    }

    public BoardStateMessage(Board val) {
        this();
        this.board = val;
    }
}
