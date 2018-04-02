package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.PlatoBrawl.tiles.BoardTiles;
import nl.han.ica.PlatoBrawl.Swordfish;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timon on 29-3-2018.
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {
	
	private boolean shootAnimation = false;
	long previousTime;
	final int animationTime = 100;
    final int size = 25;
    final float gravity = 0.05f;
    private final PlatoBrawl world;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();


    public Player(PlatoBrawl world) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 4);
        this.world = world;
        setCurrentFrameIndex(1);
        setFriction(0.01f);
        setGravity(gravity);
    }

    
    @Override
    public void keyPressed(int keyCode, char key) {
        int speed = 5;
        //Direction based stuff toevoegen
        if (keyCode == world.LEFT) {
        	setxSpeed(-speed);
        	setCurrentFrameIndex(0);
        }
        if (keyCode == world.RIGHT) {
        	setxSpeed(speed);
        	setCurrentFrameIndex(1);
        }
        if (keyCode == world.UP) {
        	setySpeed(-speed);
        }
        if (keyCode == world.DOWN) {
        	setySpeed(speed);
        }
        if (key == ' ') {
        	shootBullet();
        	shootingAnimation();
        }
    }

	private void shootingAnimation() {
    	shootAnimation = true;
    	setCorrectShootingFrame();
    	previousTime = System.currentTimeMillis();
	}


	private void setCorrectShootingFrame() {
		if (getCurrentFrameIndex() == 0) {
    		setCurrentFrameIndex(2);
    	}
    	else {
    		setCurrentFrameIndex(3);
    	}
	}


	private void shootBullet() {
		if (getCurrentFrameIndex() == 0) {
			Bullet b = new Bullet(world, -5, 1);
	    	world.addGameObject(b, getX() - getWidth(), getY());
			bulletList.add(b);
		}
    	if (getCurrentFrameIndex() == 1) {
    		Bullet b = new Bullet(world, 5, 0);
        	world.addGameObject(b, getX() + getWidth(), getY());
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
            if (ct.theTile instanceof BoardTiles) {
                if (ct.collisionSide == ct.TOP) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setY(vector.y - getHeight() - 2); // Dit omdat hij anders niet goed beweegt
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
        long currentTime = System.currentTimeMillis();
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
        if (shootAnimation == true) {
        	if (currentTime - previousTime >= animationTime) {
            	if (getCurrentFrameIndex() == 2) {
        			setCurrentFrameIndex(0);
        			shootAnimation = false;
            	}
            	if (getCurrentFrameIndex() == 3) {
        			setCurrentFrameIndex(1);
        			shootAnimation = false;
            	}
            }
        }
    }

}