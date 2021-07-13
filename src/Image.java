import javax.imageio.ImageIO;
import java.awt.*;
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

    public Image(int[][] diceArray){
        imageHeight = diceArray.length;
        imageWidth = diceArray[0].length;
        array = diceArray;
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

    public Image(String imagePath) throws IOException {
        this(getFileBufferedImage(imagePath));
    }

    private static BufferedImage getFileBufferedImage(String imagePath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
        return bufferedImage;
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

    public void toGray(){
        for (int y=0; y<imageHeight; y++){
            for (int x=0; x<imageWidth; x++){
                try {
                    setRGB(x, y, ARGBToGray(getRGB(x, y)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int naturalConversion(int red, int green, int blue){
        return (int) (0.299 * red + 0.587 * green + 0.114 * blue);
    }
    private static int ARGBToGray(int pixel){
        Color color = new Color(pixel);
        int greyValue = getGreyValue(color);
        return color.getAlpha() * 0x11000000 + greyValue * 0x00111111;
    }
    private static int getGreyValue(Color color){
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        return naturalConversion(red, green, blue);
    }

    public int[][] convertToMeanArray(int width, int height){
        int[][] meanArray = new int[height][width];
        int dx = imageHeight/height;
        int dy = imageWidth/width;

        for (int x=0; x<width; x++){
            for (int y=0; y<height; y++){
                meanArray[y][x] = getMeanGreyValueOfRectangle(x*dx, y*dy, dx, dy);
            }
        }
        return meanArray;
    }

    public static int diceConvertion(int meanValue){
        return meanValue/(255/6);
    }

    public static int[][] convertMeanArrayToDiceArray(int[][] meanArray){
        int[][] diceArray = new int[meanArray.length][meanArray[0].length];
        for (int y=0; y<meanArray.length; y++){
            for (int x=0; x<meanArray[0].length; x++){
                diceArray[y][x] = diceConvertion(meanArray[y][x]);
            }
        }
        return diceArray;
    }

    private int getMeanGreyValueOfRectangle(int x, int y, int w, int h){
        int sum = 0;
        for (int j=y; j<y+h; j++){
            for (int i=x; i<x+w; i++){
                try {
                    sum += getGreyValue(new Color(getRGB(i, j)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sum/(w*h);
    }
}
