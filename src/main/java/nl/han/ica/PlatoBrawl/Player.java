package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.PlatoBrawl.tiles.BoardsTile;
import nl.han.ica.PlatoBrawl.Swordfish;
import processing.core.PVector;

import java.util.List;

/**
 * Created by timon on 29-3-2018.
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    final int size = 25;
    final float gravity = 0.05f;
    private final PlatoBrawl world;

    public Player(PlatoBrawl world) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 2);
        this.world = world;
        setCurrentFrameIndex(1);
        setFriction(0.05f);
        setGravity(gravity);
    }

    @Override
    public void update() {
        if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY()<=0) {
            setySpeed(0);
            setY(0);
        }
        if (getX()>=world.getWidth()-size) {
            setxSpeed(0);
            setX(world.getWidth() - size);
        }
        if (getY()>=world.getHeight()-size) {
            setySpeed(0);
            setY(world.getHeight() - size);
        }

    }
    @Override
    public void keyPressed(int keyCode, char key) {
        int speed = 5;
        //Direction based stuff toevoegen
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        }
        if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(1);
        }
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
        }
        if (key == ' ') {
            System.out.println("Spatie!");
        }
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof BoardsTile) {
                if (ct.collisionSide == ct.TOP) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setY(vector.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (ct.collisionSide == ct.RIGHT) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        world.getTileMap().setTile((int) vector.x / 50, (int) vector.y / 50, -1);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    
    @Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		PVector vector;

        for (GameObject go : collidedGameObjects) {
            if (go instanceof Swordfish) {
                if (getX() >= go.getX() && getX() <= go.getX() + go.getWidth() &&
	                getY() >= go.getY() && getY() <= go.getY() + go.getHeight()	) {
                    try {
                        vector = world.getSwordfishPixelLocation();
                        setY(world.getHeight()/2);
                        setX(world.getWidth()/2);
                        setStill();
                        setCorrectCurrentFrameIndex(go);
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
             
            }
        }
		
	}
	/**
     * Kijk de vis aan
     * @param go Referentie naar de vis
     */
	private void setCorrectCurrentFrameIndex(GameObject go) {
		if (go.getCenterX() >= world.getWidth()/2) {
			setCurrentFrameIndex(1);
		}
		else {
			setCurrentFrameIndex(0);
		}
		
	}

	private void setStill() {
		setSpeed(0);
	}

}