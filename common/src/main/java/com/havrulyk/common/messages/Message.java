package com.havrulyk.common.messages;

import com.havrulyk.common.bean.Source;
import com.havrulyk.common.bean.Type;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {
    protected Type type;
    protected Source source;

    public String getCode() {
        return source.name() + "_" + type.name();
    }
}
