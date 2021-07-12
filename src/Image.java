import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private final int[][] array;
    private final int imageWidth;
    private final int imageHeight;

    public Image(int imageWidth, int imageHeight){
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        array = new int[imageHeight][imageWidth];
    }

    public Image(BufferedImage bufferedImage){
        imageHeight = bufferedImage.getHeight();
        imageWidth = bufferedImage.getWidth();
        array = new int[imageHeight][imageWidth];
        for (int y=0; y<imageHeight; y++){
            for (int x=0; x<imageWidth; x++){
                array[y][x] = bufferedImage.getRGB(x, y);
            }
        }
    }

    public void setRGB(int x, int y, int rgbValue) throws Exception {
        checkValidCoordinates(y, x);
        array[y][x] = rgbValue;
    }

    public int getRGB(int x, int y) throws Exception {
        checkValidCoordinates(y, x);
        return array[y][x];
    }

    public void writeImage(String imagePath){
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        for (int y=0; y<imageHeight; y++){
            for (int x=0; x<imageWidth; x++){
                try {
                    bufferedImage.setRGB(x, y, getRGB(x, y));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        writeBufferedImageToFile(imagePath, bufferedImage);
    }

    private void writeBufferedImageToFile(String imagePath, BufferedImage bufferedImage) {
        try {
            ImageIO.write(bufferedImage, "png", new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void checkValidCoordinates(int y, int x) throws Exception {
        if (x< 0 || x >= imageWidth || y<0 || y >= imageHeight){
            throw new Exception("invalid coordinates");
        }
    }
}
