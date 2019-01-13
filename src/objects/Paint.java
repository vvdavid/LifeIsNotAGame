package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public interface Paint {

    public default void paintBackground(Graphics g, int width, int heigth) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, heigth);
    }

    public default void paintCells(Graphics g, boolean[][] cells, int cellLength) {
//        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int gap = 0;//gap between cells
        int x = 1, y = 1; //start of grid

        for (boolean[] row : cells) {
            for (boolean cell : row) {
                if (cell) {
                    g.setColor(Color.RED);
                    g.fillOval(x, y, cellLength, cellLength);
                }
//                g.setColor(Color.BLACK);
//                g.drawRect(x, y, cellLength, cellLength);
                x += cellLength + gap;
            }
            y += cellLength + gap;
            x -= (cellLength + gap) * cells[0].length;
        }
    }
}
