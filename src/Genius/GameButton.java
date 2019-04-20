/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genius;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Samuel
 */
public class GameButton extends JButton {
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public GameButton(Color color, int x, int y, int width, int height, int index) {
        this.setBackground(color);
        this.setBounds(x, y, width, height);
        this.index = index;
    }

    public GameButton(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
    }

}
