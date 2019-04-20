/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genius;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Samuel
 */
public class Main extends JFrame {

    private List<Integer> sequencia;
    private int count = 0;
    private int rounds = 1;
    private List<GameButton> buttons = new ArrayList<GameButton>();
    private GameButton buttonStart;

    public static void main(String[] args) {
        new Main().initialize();
    }

    public void initialize() {
        prepareTemplate();        
        this.setSize(500, 500);
        this.setVisible(true);

    }

    private void prepareTemplate() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        
        GameButton botaoVerde = createButton(Color.green, 50, 50, 100, 100, 0);
        GameButton redButton = createButton(Color.RED, 325, 50, 100, 100, 1);
        GameButton yellowButton = createButton(Color.yellow, 50, 300, 100, 100, 2);
        GameButton blueButton = createButton(Color.blue, 325, 300, 100, 100, 3);

        buttonStart = createStartButton();

        jPanel.add(botaoVerde);
        jPanel.add(redButton);
        jPanel.add(yellowButton);
        jPanel.add(blueButton);
        jPanel.add(buttonStart);
        
        this.add(jPanel);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private GameButton createButton(Color color, int x, int y, int width, int height, int index) {
        GameButton gameButton = new GameButton(color, x, y, width, height, index);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClick(index);
            }
        });
        buttons.add(gameButton);
        return gameButton;
    }

    private GameButton createStartButton() {
        GameButton gameButton = new GameButton(190, 175, 100, 100);
        gameButton.setText("Start");
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        return gameButton;
    }

    private void startGame() {
        resetGame();
        next();

    }

    private void resetGame() {
        sequencia = new ArrayList<Integer>();
        rounds = 1;
        count = 0;
    }

    private void next() {
        Random random = new Random();
        int nextNumber = random.nextInt(4);
        sequencia.add(nextNumber);
        flash();
    }

    private void buttonClick(int index) {
        if (sequencia != null && sequencia.size() > 0 && count < sequencia.size()) {
            if (compareNumbers(index)) {
                count++;
                if (count == sequencia.size()) {
                    count = 0;
                    rounds++;
                    buttonStart.setText("Acertou!!");
                    next();
                }

            } else {
                resetGame();
                JOptionPane.showMessageDialog(null, "Errou :( Você estava na rodada: " + rounds);
            }
        }

    }

    private void flash() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (rounds > 1) {
                        buttonStart.setText("Acertou!!!");
                    }
                    Thread.sleep(750);
                    buttonStart.setText("Aguarde...");
                    for (int value : sequencia) {
                        Thread.sleep(500);
                        Color oldColor = buttons.get(value).getBackground();
                        buttons.get(value).setBackground(Color.WHITE);
                        Thread.sleep(500);
                        buttons.get(value).setBackground(oldColor);
                    }
                    buttonStart.setText("Sua Vez...");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    private boolean compareNumbers(int index) {
        return index == sequencia.get(count);
    }
}
