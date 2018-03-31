package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.PlatoBrawl.tiles.BoardsTile;
import nl.han.ica.PlatoBrawl.Swordfish;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timon on 29-3-2018.
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

    final int size = 25;
    final float gravity = 0.05f;
    private final PlatoBrawl world;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();


    public Player(PlatoBrawl world) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 2);
        this.world = world;
        setCurrentFrameIndex(1);
        setFriction(0.05f);
        setGravity(gravity);
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
        	shootBullet();
        }
    }

	private void shootBullet() {
		if (getCurrentFrameIndex() == 0) {
			Bullet b = new Bullet(world, -5);
	    	world.addGameObject(b, getX(), getY());
			bulletList.add(b);
		}
    	if (getCurrentFrameIndex() == 1) {
    		Bullet b = new Bullet(world, 5);
        	world.addGameObject(b, getX(), getY());
    		bulletList.add(b);
    	}   	
	}
	
	
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
                if (ct.collisionSide == ct.BOTTOM) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setySpeed(0);
                        setY(vector.y + ct.theTile.getSprite().getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (ct.collisionSide == ct.LEFT) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setxSpeed(0);
                        setX(vector.x - 1 - getWidth());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (ct.collisionSide == ct.RIGHT) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setxSpeed(0);
                        setX(vector.x + 1 + ct.theTile.getSprite().getWidth());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    
    @Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject go : collidedGameObjects) {
            if (go instanceof Swordfish) {
            	try {
                    setY(world.getHeight()/2);
                    setX(world.getWidth()/2);
                    setStill();
                    ((Swordfish) go).playerHit();
                    setCorrectCurrentFrameIndex(go);
                } catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
		
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

}