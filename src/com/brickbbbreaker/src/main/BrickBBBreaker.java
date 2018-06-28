package com.brickbbbreaker.src.main;

import java.awt.*;
import javax.swing.*;

public class BrickBBBreaker extends Canvas implements Runnable {

    public static final int HEIGHT = 320;
    public static final int WIDTH = HEIGHT / 12 * 9;
    public static final int SCALE = 2;
    public static final String TITLE = "BrickBBBreaker";

    public static void main (String args[]) {
        BrickBBBreaker brickBBBreaker = new BrickBBBreaker();

        brickBBBreaker.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        brickBBBreaker.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        brickBBBreaker.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(brickBBBreaker.TITLE);
        frame.add(brickBBBreaker);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {

    }
}
