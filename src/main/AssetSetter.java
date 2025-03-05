package main;
import object.OBJ_Carrot;

//KLASA UMIESZCZAJACA PRZEDMIOTY NA MAPIE

public class AssetSetter {
    private GamePanel gp;


    //KONSTRUKTOR
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }


    //METODY
    public void setObject(){                                        //metoda tworzy i ustawia wspolrzedne obiektow(marchewek)
        gp.obj[0] = new OBJ_Carrot();
        gp.obj[0].setWorldX(20 * gp.getTileSize());
        gp.obj[0].setWorldY(1 * gp.getTileSize());

        gp.obj[1] = new OBJ_Carrot();
        gp.obj[1].setWorldX(5 * gp.getTileSize());
        gp.obj[1].setWorldY(16 * gp.getTileSize());

        gp.obj[2] = new OBJ_Carrot();
        gp.obj[2].setWorldX(28 * gp.getTileSize());
        gp.obj[2].setWorldY(19 * gp.getTileSize());

        gp.obj[3] = new OBJ_Carrot();
        gp.obj[3].setWorldX(24 * gp.getTileSize());
        gp.obj[3].setWorldY(4 * gp.getTileSize());

        gp.obj[4] = new OBJ_Carrot();
        gp.obj[4].setWorldX(1 * gp.getTileSize());
        gp.obj[4].setWorldY(1 * gp.getTileSize());

        gp.obj[5] = new OBJ_Carrot();
        gp.obj[5].setWorldX(3 * gp.getTileSize());
        gp.obj[5].setWorldY(18 * gp.getTileSize());
    }
}
