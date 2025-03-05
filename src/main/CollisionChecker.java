package main;
import entity.Entity;

public class CollisionChecker {
    private GamePanel gp;


    //KONSTRUKTOR
    CollisionChecker(GamePanel gp){
        this.gp = gp;
    }


    //METODY
    public void checkTile(Entity entity){                                                           //uzywamy obiektu klasy Entity, bo w przyszlosci byc moze bedzieli chcieli sprawdzac kolizje np. NPCtow
        int entityLeftWorldX = entity.getWorldX() + entity.solidArea.x;
        int entityRightWorldX = entity.getWorldX() + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.getWorldY() + entity.solidArea.y;
        int entityBottomWorldY = entity.getWorldY() + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/ gp.getTileSize();
        int entityRightCol = entityRightWorldX/ gp.getTileSize();
        int entityTopRow = entityTopWorldY/ gp.getTileSize();
        int entityBottomRow = entityBottomWorldY/ gp.getTileSize();

        int tileNum1, tileNum2;                                                                     //Dwie zmienne, bo przy poruszaniu sie postaci w kazdym z kierunków wystarcza dwa kolizyjne kafelki do sprawdzenia

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed())/ gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                if(gp.getTileM().getTile()[tileNum1].collision || gp.getTileM().getTile()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/ gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if(gp.getTileM().getTile()[tileNum1].collision || gp.getTileM().getTile()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/ gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                if(gp.getTileM().getTile()[tileNum1].collision || gp.getTileM().getTile()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed())/ gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if(gp.getTileM().getTile()[tileNum1].collision || gp.getTileM().getTile()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){                      //funkcja typu int, sprawdzamy czy postac jest graczem
        int index = 999;                                                        //sprawdzamy czy gracz dotyka jakis przedmiot, jesli tak to zwracamy index tego przediotu

        for(int i=0; i<gp.obj.length; i++){
            if(gp.obj[i] != null){
                //get the entity's solid area position
                entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
                entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;
                //get the object's solid area position
                gp.obj[i].getSolidArea().x = gp.obj[i].getWorldX() + gp.obj[i].getSolidArea().x;
                gp.obj[i].getSolidArea().y = gp.obj[i].getWorldY() + gp.obj[i].getSolidArea().y;

                //symulujemy ruch postaci i sprawdzamy gdzie bedzie sie znajdowac po przesunieciu
                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        if(entity.solidArea.intersects(gp.obj[i].getSolidArea())){                   //metoda intersects wbudowana metoda rectagle
                            if(gp.obj[i].isCollision()){ entity.collisionOn = true;}
                            if(player){index = i;}
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        if(entity.solidArea.intersects(gp.obj[i].getSolidArea())){
                            if(gp.obj[i].isCollision()){ entity.collisionOn = true;}
                            if(player){index = i;}
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();
                        if(entity.solidArea.intersects(gp.obj[i].getSolidArea())){
                            if(gp.obj[i].isCollision()){ entity.collisionOn = true;}
                            if(player){index = i;}
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();
                        if(entity.solidArea.intersects(gp.obj[i].getSolidArea())){
                            if(gp.obj[i].isCollision()){ entity.collisionOn = true;}
                            if(player){index = i;}
                        }
                        break;
                }
                //resetujemy wartości
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].getSolidArea().x = gp.obj[i].getSolidAreaDefaultX();
                gp.obj[i].getSolidArea().y = gp.obj[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }
}
