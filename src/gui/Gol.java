package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import objects.Paint;

public class Gol extends javax.swing.JPanel implements Runnable, Paint {

    private final boolean[][] cells;
    private final int cellLength;
    private boolean living = true;

    public Gol(Dimension size, boolean[][] cells, int cellLength) {
        initComponents();
        setSize(size);
        this.cells = cells;
        this.cellLength = cellLength;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (living) {
            reproduce();
            sleep(1000 / 60);
            repaint();
        }
    }

    private void playGod(boolean cell) {

    }

    private void reproduce() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                playGod(cells[i][j]);
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        paintBackground(g, getWidth(), getHeight());
        paintCells(g, cells, cellLength);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

}
