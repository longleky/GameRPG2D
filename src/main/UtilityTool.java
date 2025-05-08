package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scareImage(BufferedImage original, int width, int height){
        BufferedImage scareImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scareImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scareImage;
    }
}
