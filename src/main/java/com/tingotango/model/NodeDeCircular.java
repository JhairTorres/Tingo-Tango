package com.tingotango.model;

import lombok.Data;

@Data
public class NodeDeCircular {
    private Kid data;
    private NodeDeCircular next;
    private NodeDeCircular previous;

    public NodeDeCircular(Kid data) {
        this.data = data;
    }
}
