package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import objects.Paint;
import objects.Properties;

public class Gol extends javax.swing.JPanel implements Runnable, Paint {

    private boolean[][] cells;
    int cellLength, gap, shape;
    int deaths = 0, births = 0, generations = 0;
    private final Properties props;
    private final ArrayList<Point> toLive = new ArrayList<>();
    private final ArrayList<Point> toDie = new ArrayList<>();

    public boolean runningThread = true;
    Thread reproduce = new Thread(this);
    private final int estimatedFps = 25;

    public Gol(Dimension size, boolean[][] cells, int cellLength, int gap, int shape, Properties props) {
//        super(true);
        initComponents();
        setSize(size);

        this.cells = arrCopy(cells);
        this.cellLength = cellLength;
        this.gap = gap;
        this.shape = shape;
        this.props = props;

        reproduce.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void run() {
        while (runningThread) {
            nextGen();
            sleep(1000 / estimatedFps);
            repaint();
        }
    }

    private void nextGen() {
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[0].length; column++) {
                playGod(row, column, neighbors(row, column));
            }
        }

        toDie.forEach((p) -> {
            cells[p.x][p.y] = false;
        });

        toLive.forEach((p) -> {
            cells[p.x][p.y] = true;
        });

        toDie.clear();
        toLive.clear();

        props.addGeneration(generations++);
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
        if (cells[row][column] && (neighbors < 2 || neighbors > 3)) {
            toDie.add(new Point(row, column));
            props.addDeath(deaths++);
        } else if (neighbors == 3) {
            toLive.add(new Point(row, column));
            props.addBirth(births++);
        }
    }

    @Override
    public void paint(Graphics g) {
        paintBackground(g, getWidth(), getHeight());
        paintCells(g, cells, cellLength, gap, shape);
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

    /**
     * Update cells and newCells of this gol gameboard
     *
     * @param cells cells to assign
     */
    public void setCells(boolean[][] cells) {
        this.cells = cells;
    }
}
