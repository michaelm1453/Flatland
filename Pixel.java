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
            f = new File("skeleton.png");
            img = ImageIO.read(f);
            p = img.getRGB(x,y);
            //img.setRGB(x,y, -1237890);
            System.out.println("X:" + x + "Y:" + y);
            System.out.println(p);
        }
        catch(IOException e){
            System.out.println(e);
        }

        if(p < -1)
            return false;
        else
            return true;

    }

}