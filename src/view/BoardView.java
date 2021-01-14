package view;

import utilities.Game;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    final int fontSize = 30;
    final Font font = new Font("", Font.BOLD, fontSize);
    final Font smallerFont = new Font("", Font.BOLD, fontSize - 5);

    int canvasXCenterPosition;
    int canvasYCenterPosition;

    final int boardDimensions = 250;
    final int strokeWidth = 7;

    int leftX;
    int rightX;
    int topY;
    int bottomY;

    int tileDimensions;


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        useAntialiasing(g2d);

        Dimension size = getSize();

        int xOffset = 0; // in case i need to move the board in x or y axis
        int yOffset = 50;

        // get center of JPanel
        canvasXCenterPosition = (int) size.getWidth() / 2 + xOffset;
        // in case i need to move the board on x or y axis
        canvasYCenterPosition = (int) size.getHeight() / 2 + yOffset;

        leftX = canvasXCenterPosition - boardDimensions;
        rightX = canvasXCenterPosition + boardDimensions;
        topY = canvasYCenterPosition - boardDimensions;
        bottomY = canvasYCenterPosition + boardDimensions;

        tileDimensions = (rightX - leftX) / Game.BOARD_CONTROLLER.getBoardSize();

        drawBackground(g2d);

        drawTiles(g2d);
        drawGrid(g2d);
        drawScore(g2d);
        drawTimer(g2d);
        drawPlayerName(g2d);
    }

    /**
     * Use antialiasing for smoother pixels
     *
     * @param g2d Graphics2D
     */
    private void useAntialiasing(Graphics2D g2d) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
    }

    /**
     * Draws a tile on the board given a x and y position
     *
     * @param g2d   Graphics2D
     * @param x     Position
     * @param y     Position
     * @param value Value to print on tile
     */
    private void drawTile(Graphics2D g2d, int x, int y, String value) {
        // check if x or y is out of bounds
        if (x >= Game.BOARD_CONTROLLER.getBoardSize() || y >= Game.BOARD_CONTROLLER.getBoardSize()) {
            return;
        }

        int tileLeftX = leftX + (x * tileDimensions);
        int tileTopY = topY + (y * tileDimensions);
        if (Palette.tileColors.containsKey(value)) {
            g2d.setColor(Palette.tileColors.get(value));
        } else {
            g2d.setColor(Palette.tileColors.get("2048")); // if value is above 2048 keep drawing with the 2048 color
        }
        g2d.fillRect(tileLeftX, tileTopY, tileDimensions, tileDimensions);

        // if value is above 8 change font color
        if (Integer.parseInt(value) < 8) {
            g2d.setColor(Palette.fontColor1);
        } else {
            g2d.setColor(Palette.fontColor2);
        }

        if (Integer.parseInt(value) > 100) {
            drawCenteredString(
                    g2d,
                    value,
                    new Rectangle(tileLeftX, tileTopY, tileDimensions, tileDimensions),
                    smallerFont
            );
            return;
        }

        drawCenteredString(
                g2d,
                value,
                new Rectangle(tileLeftX, tileTopY, tileDimensions, tileDimensions),
                font
        );

    }

    private void drawTiles(Graphics2D g2d) {
        for (int i = 0; i < Game.BOARD_CONTROLLER.getBoardSize(); i++) {
            for (int j = 0; j < Game.BOARD_CONTROLLER.getBoardSize(); j++) {
                if (Game.BOARD_CONTROLLER.isTileNotEmpty(i, j)) {
                    drawTile(g2d, i, j, String.valueOf(Game.BOARD_CONTROLLER.getTileValue(i, j)));
                }
            }
        }
    }

    private void drawPlayerName(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Palette.fontColor1);
        String scoreString = "Player: " + Game.getPlayerName();
        g2d.drawString(scoreString, leftX, topY - 85);
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Palette.fontColor1);
        String scoreString = "Score: " + Game.BOARD_CONTROLLER.getScore();
        g2d.drawString(scoreString, leftX, topY - 50);
    }

    private void drawTimer(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Palette.fontColor1);

        long milliseconds = Game.BOARD_CONTROLLER.getTime();
        long minutes = (milliseconds / 1000) / 60;
        int seconds = (int) ((milliseconds / 1000) % 60);
        String timerString = String.format("Time: %02d:%02d", minutes, seconds);
        g2d.drawString(timerString, leftX, topY - 15);
    }


    /**
     * view.Draw a String centered in the middle of a Rectangle.
     *
     * @param g2d  The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public void drawCenteredString(Graphics2D g2d, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g2d.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g2d.setFont(font);
        // view.Draw the String
        g2d.drawString(text, x, y);
    }

    /**
     * Draws the board background
     *
     * @param g2d Graphics2d
     */
    private void drawBackground(Graphics2D g2d) {
        g2d.setColor(Palette.backgroundColor);
        g2d.fillRect(leftX, topY, boardDimensions * 2, boardDimensions * 2);
    }

    /**
     * Draws the board grid
     *
     * @param g2d Graphics2D
     */
    private void drawGrid(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.setColor(Palette.foregroundColor);
        g2d.drawRoundRect(leftX, topY, boardDimensions * 2, boardDimensions * 2, 2, 2);

        g2d.setStroke(new BasicStroke(strokeWidth));

        // draw rows
        for (int i = 1; i < Game.BOARD_CONTROLLER.getBoardSize(); i++) {
            g2d.drawLine(leftX, (i * tileDimensions) + topY, rightX, (i * tileDimensions) + topY);
        }

        // draw the columns
        for (int i = 1; i < Game.BOARD_CONTROLLER.getBoardSize(); i++) {
            g2d.drawLine((i * tileDimensions) + leftX, topY, (i * tileDimensions) + leftX, bottomY);
        }
    }
}
