package com.tingotango.model;

import lombok.Data;

@Data
public class ListDECircular {
    private NodeDeCircular head;
    private NodeDeCircular tail;
    private int size;

    // Método para agregar un niño al inicio de la lista
    public void addToStart(Kid kid) {
        NodeDeCircular newNode = new NodeDeCircular(kid);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }

        size++;
    }

    // Método para agregar un niño al final de la lista
    public void addToEnd(Kid kid) {
        NodeDeCircular newNode = new NodeDeCircular(kid);

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }
    public void addAtPosition(Kid kid, int position) {
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Posición fuera de rango");
        }

        NodeDeCircular newNode = new NodeDeCircular(kid);

        if (position == 0) {
            addToStart(kid);
        } else if (position == size) {
            addToEnd(kid);
        } else {
            NodeDeCircular current = getNodeAtPosition(position);
            NodeDeCircular previous = current.getPrevious();

            previous.setNext(newNode);
            newNode.setPrevious(previous);

            newNode.setNext(current);
            current.setPrevious(newNode);

            size++;
        }
    }

    // Método para mover un niño a una posición específica
    public void moveKidToPosition(NodeDeCircular node, int newPosition) {
        // Obtener la posición actual del niño
        int currentPosition = getPositionOfNode(node);

        // Eliminar el niño de la posición actual
        removeKidAtPosition(currentPosition);

        // Agregar el niño en la nueva posición
        addAtPosition(node.getData(), newPosition);
    }

    // Método para eliminar un niño en una posición específica
    public void removeKidAtPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Posición fuera de rango");
        }

        if (size == 0) {
            throw new IllegalStateException("La lista está vacía");
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else if (position == 0) {
            head = head.getNext();
            head.setPrevious(tail);
            tail.setNext(head);
        } else if (position == size - 1) {
            tail = tail.getPrevious();
            tail.setNext(head);
            head.setPrevious(tail);
        } else {
            NodeDeCircular current = getNodeAtPosition(position);
            NodeDeCircular previous = current.getPrevious();
            NodeDeCircular next = current.getNext();

            previous.setNext(next);
            next.setPrevious(previous);
        }

        size--;
    }

    // Método auxiliar para obtener el nodo en una posición específica
    public NodeDeCircular getNodeAtPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Posición fuera de rango");
        }

        NodeDeCircular current = head;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }

        return current;
    }

    // Método auxiliar para obtener la posición de un nodo en la lista
    public int getPositionOfNode(NodeDeCircular node) {
        NodeDeCircular current = head;
        int position = 0;

        while (current != null && current != node) {
            current = current.getNext();
            position++;
        }

        if (current == null) {
            throw new IllegalArgumentException("El nodo no pertenece a la lista");
        }

        return position;
    }
}


