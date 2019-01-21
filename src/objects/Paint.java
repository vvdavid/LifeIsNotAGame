package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.concurrent.ThreadLocalRandom;

public interface Paint {

    public default void paintBackground(Graphics g, int width, int heigth) {
//        g.setColor(c);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, heigth);
    }

    /**
     * Paints the given cells 2D array with pastell colors using the Graphics
     * object. Has the ability to draw a gap between every cell Cell size
     * specified in pixels
     *
     * @param g
     * @param cells
     * @param cellLength
     * @param gap
     * @param shape
     */
    public default void paintCells(Graphics g, boolean[][] cells, int cellLength, int gap, int shape) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final int x = 1, y = 1;
        int actualX = 1, actualY = 1; //start of grid

        for (boolean[] row : cells) {
            for (boolean cell : row) {
                if (cell) {
                    g.setColor(new Color(
                            ThreadLocalRandom.current().nextInt(0, 128) + 127,
                            ThreadLocalRandom.current().nextInt(0, 128) + 127,
                            ThreadLocalRandom.current().nextInt(0, 128) + 127
                    ));
                    switch (shape) {
                        case 0:
                            g.fillOval(actualX, actualY, cellLength, cellLength);
                            break;
                        case 1:
                            g.fillRect(actualX, actualY, cellLength, cellLength);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    g.setColor(new Color(
                            ThreadLocalRandom.current().nextInt(0, 128) + 127,
                            ThreadLocalRandom.current().nextInt(0, 128) + 127,
                            ThreadLocalRandom.current().nextInt(0, 128) + 127
                    ));
                    switch (shape) {
                        case 0:
                            g.fillOval(
                                    actualX + (cellLength / 4),
                                    actualY + (cellLength / 4),
                                    cellLength / 2,
                                    cellLength / 2);
                            break;
                        case 1:
                            g.fillRect(
                                    actualX + (cellLength / 4),
                                    actualY + (cellLength / 4),
                                    cellLength / 2,
                                    cellLength / 2);
                            break;
                        default:
                            throw new AssertionError();
                    }

                }
                actualX += cellLength + gap;
            }
            actualY += cellLength + gap;
            actualX = x;
        }
    }
}
