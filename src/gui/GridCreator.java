package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.ThreadLocalRandom;
import objects.Paint;

public class GridCreator extends javax.swing.JPanel implements Paint {

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
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = ThreadLocalRandom.current().nextBoolean();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        paintBackground(g, getWidth(), getHeight());
        paintCells(g, cells, cellLength);
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
