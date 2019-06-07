package graphics;

import java.awt.*;
import javax.swing.*;

public class HexagonPattern extends JPanel {
    private static final long serialVersionUID = 1L;
    public int ROWS = 200;
    public int COLUMNS = 200;
    public HexagonButton[][] hexButton;
    private int W;


    public HexagonPattern() {
        setLayout(null);
    }


    public void initGUI() {
        hexButton = new HexagonButton[ROWS][COLUMNS];
        W = 500 / Math.max(ROWS, (COLUMNS + 1));
        int offsetX = 0;
        int offsetY = 0;

        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++){
                hexButton[row][col] = new HexagonButton(row, col);
                add(hexButton[row][col]);
                hexButton[row][col].setBounds(offsetX, offsetY, W, W);
                offsetX += W;
            }
            if(row%2 == 0) {
                offsetX = W / 2;
            } else {
                offsetX = 0;
            }
            offsetY += W * 0.9;
        }
    }

    //Following class draws the Buttons
    public class HexagonButton extends JButton {
        private static final long serialVersionUID = 1L;
        private static final int SIDES = 6;
        private final int SIDE_LENGTH = W / 2;
        final int LENGTH = 2 * SIDE_LENGTH;
        final int WIDTH = (int) 2.1 * SIDE_LENGTH;
        int ANGLE_ROTATE = 30;
        double ROTATE = (Math.PI / 180) * ANGLE_ROTATE;
        private int row = 0;
        private int col = 0;

        HexagonButton(int row, int col) {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            setText(" ");
            this.row = row;
            this.col = col;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            for (int i = 0; i < SIDES; i++) {
                hex.addPoint((int) (W / 2 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES + ROTATE)), //calculation for side
                        (int) (W / 2 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES + ROTATE)));   //calculation for side
            }
            g.drawPolygon(hex);
        }
    }
}