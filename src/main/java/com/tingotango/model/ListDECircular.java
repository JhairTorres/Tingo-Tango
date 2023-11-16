package com.tingotango.model;

import com.tingotango.exceptions.GameExceptions;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDECircular {
    private NodeDeCircular head;
    private int size;
    public void addToStart(Kid kid){
        NodeDeCircular newNode = new NodeDeCircular(kid);
        if (this.head ==null){
            this.head=newNode;
            this.head.setPrevious(this.head);
            this.head.setNext(this.head);
        }
        else{
            NodeDeCircular lastNode = this.head.getPrevious();
            this.head.setPrevious(newNode);
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
            newNode.setNext(this.head);
            this.head=newNode;
        }
        this.size++;


    }

    public void addToEnd(Kid kid){
        NodeDeCircular newNode = new NodeDeCircular(kid);
        if(this.head==null){
            this.head =newNode;
            this.head.setNext(this.head);
            this.head.setPrevious(this.head);
        } else {
            this.head.getPrevious().setNext(newNode);
            newNode.setPrevious(this.head.getPrevious());
            this.head.setPrevious(newNode);
            newNode.setNext(this.head);

        }
        this.size++;
    }

    public List<Kid> getAll() throws GameExceptions {
        if(this.head==null){
            throw new GameExceptions("Lista vacia");
        }
        else{
            List<Kid> kids = new ArrayList<>();
            NodeDeCircular temp = this.head;
            do{
                kids.add(temp.getData());
                temp = temp.getNext();
            }
            while (temp!=this.head);
            return kids;
        }
    }
    public void insertInPos (int pos, Kid kid) {
        if (pos == 1) {
            this.addToStart(kid);

        } else if (pos > this.size) {
            this.addToEnd(kid);

        } else if (pos <= this.size) {
            NodeDeCircular temp = this.head;
            int posAct = 1;
            while (posAct < pos - 1) {
                temp = temp.getNext();
                posAct++;
            }
            NodeDeCircular newNode = new NodeDeCircular(kid);
            temp.getNext().setPrevious(newNode);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            this.size++;
        }

    }

    public void deleteByPos (int pos) throws GameExceptions {
        if (pos <= 0 || pos > this.size) {
            throw new GameExceptions("Fuera de rango");
        }
        if(pos == 1){
            NodeDeCircular lastNode = this.head.getPrevious();
            lastNode.setNext(this.head.getNext());
            this.head.setPrevious(lastNode);
            this.head = this.head.getNext();
        }
        else{
            NodeDeCircular temp = this.head;
            int cont = 1;

            while (cont < pos -1){
                temp = temp.getNext();
                cont++;
            }
            temp.getNext().getNext().setPrevious(temp);
            temp.setNext(temp.getNext().getNext());

        }
        this.size--;
        System.out.println("New size value: "+this.size);
    }

    public NodeDeCircular deleteById(String id) throws GameExceptions {
        if (this.head == null) {
            throw new GameExceptions("Lista vacia");
        } else {
            NodeDeCircular temp = this.head;
            do {
                if (temp.getData().getId().equals(id)) {
                    // If node to be deleted is head node
                    if (temp == this.head) {
                        this.head = this.head.getNext();
                        this.head.setPrevious(temp.getPrevious());
                    }
                    // Change next only if node to be deleted is NOT the last node
                    if (temp.getNext() != null) {
                        temp.getNext().setPrevious(temp.getPrevious());
                    }
                    // Change prev only if node to be deleted is NOT the first node
                    if (temp.getPrevious() != null) {
                        temp.getPrevious().setNext(temp.getNext());
                    }
                    this.size--;
                    return temp;
                }
                temp = temp.getNext();
            } while (temp != this.head);
            throw new GameExceptions("ID not found");
        }
    }


    public void moveKid (int pos, String kidId) throws GameExceptions{
        int actualPos = pos%this.size;
        try {
            NodeDeCircular deletedKid = this.deleteById(kidId);
            this.insertInPos(actualPos,deletedKid.getData());
        } catch (GameExceptions e) {
            throw new GameExceptions(e.getMessage());
        }



    }

}

