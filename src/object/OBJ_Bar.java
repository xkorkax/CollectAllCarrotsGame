package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Bar extends SuperObject{
    public OBJ_Bar() {
        setName("bar");
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/bar0.png")));
            setImage1(ImageIO.read(getClass().getResourceAsStream("/objects/bar1.png")));
            setImage2(ImageIO.read(getClass().getResourceAsStream("/objects/bar2.png")));
            setImage3(ImageIO.read(getClass().getResourceAsStream("/objects/bar3.png")));
            setImage4(ImageIO.read(getClass().getResourceAsStream("/objects/bar4.png")));
            setImage5(ImageIO.read(getClass().getResourceAsStream("/objects/bar5.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
