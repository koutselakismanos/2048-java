package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                Game.BOARD.moveUp();
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                Game.BOARD.moveDown();
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                Game.BOARD.moveLeft();
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                Game.BOARD.moveRight();
                break;
            default:
                return;
        }

        Game.BOARD.insertRandomTile();
        Game.WINDOW.repaint();
    }
}
