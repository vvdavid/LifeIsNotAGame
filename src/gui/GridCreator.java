package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GridCreator extends javax.swing.JPanel {

    private boolean[][] cells;
    int cellLength = 110;

    public GridCreator(Dimension panelDimension) {
        initComponents();
        addListeners();
        //set size and set cell number
        setSize(panelDimension);
        int rows = getHeight() / cellLength;
        int columns = getWidth() / cellLength;
        this.cells = new boolean[rows][columns];
    }

    @Override
    public void paint(Graphics g) {
        paintBackground(g);
        paintCells(g, cells, cellLength);
    }

    public static void paintCells(Graphics g, boolean[][] cells, int cellLength) {

        int gap = 0;//gap between cells
        int x = 1, y = 1; //start of grid

        for (boolean[] row : cells) {
            for (boolean cell : row) {
                if (cell) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, cellLength, cellLength);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellLength, cellLength);
                x += cellLength + gap;
            }
            y += cellLength + gap;
            x -= (cellLength + gap) * cells[0].length;
        }

    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                invierte(e);
            }
        });

    }

    private void invierte(MouseEvent e) {
        try {
            int row = e.getY() / cellLength;
            int column = e.getX() / cellLength;
            cells[row][column] = !cells[row][column];
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        repaint();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public boolean[][] getCells() {
        return cells;
    }

}
