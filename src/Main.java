import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4){
            System.err.println("Arguments error! Usage: {source_image} {destination_image} {width} {height}");
            System.exit(8);
        }

        String inputFile = args[0];
        String outputFile = args[1];
        int width = Integer.parseInt(args[2]);
        int height = Integer.parseInt(args[3]);

        try {
            Image source = new Image(inputFile);
            source.generateDiceArt(width, height, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
