package com.tingotango.model;

import lombok.Data;

@Data
public class NodeDeCircular {
    private Kid data;
    private NodeDeCircular previous;
    private NodeDeCircular Next;
    public NodeDeCircular(Kid data) {
        this.data = data;
    }
}
