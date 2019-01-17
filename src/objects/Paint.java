package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.UIManager;

public interface Paint {

    public default void paintBackground(Graphics g, int width, int heigth) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, heigth);
    }

    public default void paintCells(Graphics g, boolean[][] cells, int cellLength, int gap) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final int x = 1, y = 1;
        int actualX = 1, actualY = 1; //start of grid

        for (boolean[] row : cells) {
            for (boolean cell : row) {
                if (cell) {
                    g.setColor(Color.RED);
                    g.fillOval(actualX, actualY, cellLength, cellLength);
                }
                actualX += cellLength + gap;
            }
            actualY += cellLength + gap;
            actualX = x;
        }
    }
}
