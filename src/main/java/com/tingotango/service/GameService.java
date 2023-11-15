package com.tingotango.service;

import com.tingotango.model.Kid;
import com.tingotango.model.ListDECircular;
import com.tingotango.model.NodeDeCircular;

import java.util.Random;

public class GameService {
    private ListDECircular kidsList;

    public GameService() {
        // Inicializar la lista de niños desde un archivo TXT u otra fuente
        // Puedes usar FileReader, BufferedReader, etc.
        // Luego, crea nodos y agrega los niños a la lista circular
        // kidsList = initializeListFromTxt("ruta_del_archivo.txt");
    }

    // Método para agregar un niño al inicio de la lista (antes de comenzar el juego)
    public void addKidToStart(Kid kid) {
        kidsList.addToStart(kid);
    }

    // Método para agregar un niño al final de la lista (antes de comenzar el juego)
    public void addKidToEnd(Kid kid) {
        kidsList.addToEnd(kid);
    }

    // Método para agregar un niño en una posición específica
    public void addKidAtPosition(Kid kid, int position) {
        kidsList.addAtPosition(kid, position);
    }

    // Método para mover un niño a una posición específica
    public void moveKidToPosition(NodeDeCircular node, int newPosition) {
        kidsList.moveKidToPosition(node, newPosition);
    }

    // Método para eliminar un niño en una posición específica
    public void removeKidAtPosition(int position) {
        kidsList.removeKidAtPosition(position);
    }

    // Método para iniciar el juego y realizar la lógica del juego
    public void startGame() {
        if (kidsList == null || kidsList.getSize() < 1) {
            throw new IllegalStateException("La lista de niños está vacía");
        }
        Random random = new Random();
        // Seleccionar un niño al azar de la lista
        NodeDeCircular selectedKidNode = getRandomKidNode();

        while (kidsList.getSize() > 1) {
            // Lógica para hacer preguntas aleatorias al niño seleccionado

            // Si el niño responde correctamente, elige otro niño aleatorio
            if (random.nextBoolean()) {
                selectedKidNode = getRandomKidNode();
            } else {
                // Si el niño responde incorrectamente, elimínalo de la lista
                NodeDeCircular nextKidNode = selectedKidNode.getNext();
                kidsList.removeKidAtPosition(kidsList.getPositionOfNode(selectedKidNode));
                selectedKidNode = nextKidNode;
            }
        }

        // El último niño restante es el ganador
        Kid winner = kidsList.getHead().getData();
        System.out.println("¡El ganador es: " + winner.getName() + "!");
    }

    // Método para obtener un nodo de niño aleatorio de la lista
    private NodeDeCircular getRandomKidNode() {
        Random random = new Random();
        int randomPosition = random.nextInt(kidsList.getSize());
        return kidsList.getNodeAtPosition(randomPosition);
    }

    // Otros métodos según sea necesario...
}

