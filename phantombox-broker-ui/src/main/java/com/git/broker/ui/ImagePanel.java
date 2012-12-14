package com.git.broker.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Image panel.
 * <p/>
 * Date: 14.12.12
 * Time: 17:15
 *
 * @author rpleshkov
 */
public class ImagePanel extends JPanel {

    public static final int X = 0;
    public static final int Y = 0;
    private BufferedImage image;

    /**
     * Constructor with parameters.
     *
     * @param image {@link BufferedImage}
     */
    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, X, Y, null);
    }

}
