import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Pixel
{
    public static boolean getPixel(int x, int y){
        BufferedImage img = null;
        File f = null;
        int p = 0;

        try{
            f = new File("skeleton.png"); //reads the skeleton file
            img = ImageIO.read(f);
            p = img.getRGB(x,y); //gets the color for the pixel
        }
        catch(IOException e){
            System.out.println(e);
        }

        if(p < -1) //-1 is the code for white, so anything less than that is no walking zone
            return true;
        else
            return false;

    }

}