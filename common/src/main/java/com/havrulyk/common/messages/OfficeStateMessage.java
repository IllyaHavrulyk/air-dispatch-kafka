package com.havrulyk.common.messages;

import com.havrulyk.common.bean.Source;
import com.havrulyk.common.bean.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfficeStateMessage extends Message {

    public OfficeStateMessage() {
        this.source = Source.OFFICE;
        this.type = Type.STATE;
    }
}
