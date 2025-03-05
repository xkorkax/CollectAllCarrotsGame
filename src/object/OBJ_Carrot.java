package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Carrot extends SuperObject{
    public OBJ_Carrot() {
        setName("carrot");
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/carrot.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
