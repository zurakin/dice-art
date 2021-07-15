package imageConversion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceImage {
    private final int width;
    private final int height;
    private BufferedImage bufferedImage;

    public DiceImage(String imagePath) throws IOException {
        bufferedImage = ImageIO.read(new File(imagePath));
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void collage(BufferedImage destinationBufferedImage, int x, int y){
        int []rgb = bufferedImage.getRGB(0, 0, width, height, null, 0, width*height);
        destinationBufferedImage.setRGB(x, y, width, height, rgb, 0, width*height);
    }
}
