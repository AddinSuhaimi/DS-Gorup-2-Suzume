package pixelmap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
