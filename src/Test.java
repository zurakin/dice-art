import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        DiceArtTest();
    }

    private static void copyTranslucentImageTest(){
        try {
            Image image = new Image("images/cardJoker.png");
            image.writeImage("images/cardJokerCopy.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void toGrayTest(){
        try {
            Image image = new Image("images/cardJoker.png");
            image.toGray();
            image.writeImage("images/cardJokerGray.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayDiceArray(int[][] diceArray){

        for (int[] l: diceArray){
            for (int x: l){
                System.out.print(x+" ");
            }
            System.out.println("");
        }
    }


    private static void writeTranslucentImageTest() {
        Image image = new Image(100, 100);
        for (int y=0; y<100; y++){
            for (int x=0; x<100; x++){
                try {
                    image.setRGB(x, y, 0x5500ffff);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        image.writeImage("images/output.png");
    }

    public static void collageTest() {
        try {
            BufferedImage joker = ImageIO.read(new File("images/cardJoker.png"));
            DiceImage dice = new DiceImage("images/dice/dieWhite1.png");
            dice.collage(joker, 0, 0);
            ImageIO.write(joker, "png", new File("images/joker-dice-collage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void diceArrayToCollage(){
        int[][] diceArray = new int[3][3];
        for (int y=0; y<3; y++){
            for (int x=0; x<3; x++){
                diceArray[y][x] = x+y+1;
            }
        }
        try {
            Image.writeBufferedImageToFile("images/diceToCollageTest.png", Image.convertDiceArrayToBufferedImage(diceArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DiceArtTest(){
        try {
            Image source = new Image("images/cardJoker.png");
            source.generateDiceArt(28, 38, "images/cardJokerDiceArt.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
