/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pixelmap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DisplayPixelMap extends JPanel {

    private BufferedImage pixelMap;
    private int widthDisplay;
    private int heightDisplay;

    public DisplayPixelMap(BufferedImage pixelMap) {
        this.pixelMap = pixelMap;
        widthDisplay = pixelMap.getWidth();
        heightDisplay = pixelMap.getHeight();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(pixelMap, 0, 0, null);
    }
}