package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class Ikona implements Icon
{	
	public Ikona(String string)
	{
		try {
			img = ImageIO.read(new File(string));
		} catch (IOException e)
		{
			System.out.println("Nie mozna wczytac obrazka!");
		}
	}
	public int getIconHeight()
	{
		return img.getHeight();
	}

	public int getIconWidth()
	{
		return img.getWidth();
	}

	public void paintIcon(Component arg0, Graphics g, int arg2, int arg3)
	{		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, null);	
	}
	
	private BufferedImage img;
}