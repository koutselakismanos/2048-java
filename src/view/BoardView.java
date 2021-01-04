package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    final int fontSize = 30;
    final Font font = new Font("", Font.BOLD, fontSize);

    int canvasXCenterPosition;
    int canvasYCenterPosition;

    final int boardDimensions = 200;
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
        int yOffset = 0;

        // get center of JPanel
        canvasXCenterPosition = (int) size.getWidth() / 2 + xOffset;
        // in case i need to move the board on x or y axis
        canvasYCenterPosition = (int) size.getHeight() / 2 + yOffset;

        leftX = canvasXCenterPosition - boardDimensions;
        rightX = canvasXCenterPosition + boardDimensions;
        topY = canvasYCenterPosition - boardDimensions;
        bottomY = canvasYCenterPosition + boardDimensions;

        tileDimensions = (rightX - leftX) / Game.BOARD.getBoardSize();

        drawBackground(g2d);

//        drawTile(g2d, 0, 0, "16");
//        drawTile(g2d, 0, 2, "2");
//        drawTile(g2d, 3, 2, "2048");
//        drawTile(g2d, 2, 0, "4");
//        drawTile(g2d, 3, 0, "4");
        drawTiles(g2d);

        drawGrid(g2d);
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
     * @param g2d  Graphics2D
     * @param x    Position
     * @param y    Position
     * @param text Text print on tile
     */
    private void drawTile(Graphics2D g2d, int x, int y, String text) {
        // check if x or y is out of bounds
        if (x >= Game.BOARD.getBoardSize() || y >= Game.BOARD.getBoardSize()) {
            return;
        }

        int tileLeftX = leftX + (x * tileDimensions);
        int tileTopY = topY + (y * tileDimensions);
        g2d.setColor(Palette.twoTileColor);
        g2d.fillRoundRect(tileLeftX, tileTopY, tileDimensions, tileDimensions, 4, 4);

        g2d.setColor(Palette.fontColor1);
        drawCenteredString(
                g2d,
                text,
                new Rectangle(tileLeftX, tileTopY, tileDimensions, tileDimensions),
                font
        );
    }

    private void drawTiles(Graphics2D g2d) {
        for (int i = 0; i < Game.BOARD.getBoardSize(); i++) {
            for (int j = 0; j < Game.BOARD.getBoardSize(); j++) {
                if (!Game.BOARD.isTileEmpty(i, j)) {
                    drawTile(g2d, i, j, String.valueOf(Game.BOARD.getTileValue(i, j)));
                }
            }
        }
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
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
        // Draw the String
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
        for (int i = 1; i < Game.BOARD.getBoardSize(); i++) {
            g2d.drawLine(leftX, (i * tileDimensions) + topY, rightX, (i * tileDimensions) + topY);
        }

        // draw the columns
        for (int i = 1; i < Game.BOARD.getBoardSize(); i++) {
            g2d.drawLine((i * tileDimensions) + leftX, topY, (i * tileDimensions) + leftX, bottomY);
        }
    }
}
