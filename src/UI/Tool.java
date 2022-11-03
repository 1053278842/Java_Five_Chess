package UI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tool {
	public static BufferedImage getImage(String path) {
		BufferedImage img;
		try {
			img = ImageIO.read(Tool.class.getResource(path));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
