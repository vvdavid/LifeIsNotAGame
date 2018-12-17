/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Admin
 */
public interface Paint {

    public default void paintBackground(Graphics g, int width, int heigth) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, heigth);
    }

    public default void paintCells(Graphics g, boolean[][] cells, int cellLength) {
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
