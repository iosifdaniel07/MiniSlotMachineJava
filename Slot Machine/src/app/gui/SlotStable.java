package app.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SlotStable extends JPanel {

    private BufferedImage _image;

    public SlotStable(BufferedImage _image){
        this._image = _image;
    }

    @Override

    public Dimension getPreferredSize() {
       return new Dimension(118,118);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
          g2d.drawImage(_image,0,0,this);

    }

    public void setImage(BufferedImage image){
        _image = image;
    }

}
