import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        copyTranslucentImageTest();
    }

    private static void copyTranslucentImageTest(){
        try {
            Image image = new Image("images/cardJoker.png");
            image.writeImage("images/cardJokerCopy.png");
        } catch (IOException e) {
            e.printStackTrace();
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
}
