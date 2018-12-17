package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import objects.Paint;

public class Gol extends javax.swing.JPanel implements Runnable, Paint {

    private boolean[][] cells;
    private boolean[][] newCells;
    private final int cellLength;

    public boolean living = true;

    public Gol(Dimension size, boolean[][] cells, int cellLength) {
        initComponents();
        setSize(size);

        this.cells = arrCopy(cells);
        this.newCells = arrCopy(cells);
        this.cellLength = cellLength;

        //print neighbors of clicked cell (debug purposes)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int f = e.getY() / cellLength;
                    int c = e.getX() / cellLength;
                    System.out.println("vecinos en " + f + "," + c + ": " + neighbors(f, c));

                } catch (ArrayIndexOutOfBoundsException ex) {
                }
            }

        });

        //start painting the game!
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    @Override
    public void run() {
        while (living) {
            nextGen();
            sleep(1000 / 30);
            repaint();
        }
    }

    private void nextGen() {
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                playGod(row, column, neighbors(row, column));
            }
        }
        this.cells = arrCopy(newCells);
    }

    private int neighbors(int row, int column) {
        int neighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                try {
                    if (cells[row + i][column + j]) {
                        neighbors++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return neighbors;
    }

    private void playGod(int row, int column, int neighbors) {
        if (cells[row][column]) {
            if (neighbors < 2) {
                newCells[row][column] = false;
            } else if (neighbors == 2 || neighbors == 3) {
                newCells[row][column] = true;
            } else if (neighbors > 3) {
                newCells[row][column] = false;
            }
        } else {
            if (neighbors == 3) {
                newCells[row][column] = true;
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

    private boolean[][] arrCopy(boolean[][] src) {
        boolean[][] dest = new boolean[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i].clone();
        }
        return dest;
    }
}
