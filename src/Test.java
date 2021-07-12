public class Test {

    public static void main(String[] args) {
        writeTransparentImageTest();
    }

    private static void writeTransparentImageTest() {
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
        image.writeImage("output.png");
    }
}
