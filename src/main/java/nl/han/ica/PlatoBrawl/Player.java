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

import java.util.List;

/**
 * Created by timon on 29-3-2018.
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {
	
	private boolean isShooting = false;
	private boolean isBumping = false;
	long animationStart;
	final int animationTime = 100;
    final int size = 25;
    final float gravity = 0.05f;
    private final PlatoBrawl world;
    protected float hitpoints;


    public Player(PlatoBrawl world) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 4);
        this.world = world;
        setCurrentFrameIndex(1);
        setFriction(0.05f);
        setGravity(gravity);
        this.hitpoints = 10;
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
        	setDirectionSpeed(90, speed);
        	setCurrentFrameIndex(1);
        }
        if (keyCode == world.UP) {
        	setySpeed(-speed);
        }
        if (keyCode == world.DOWN) {
        	setDirectionSpeed(180, speed);
        }
        if (key == ' ') {
        	shootBullet();
        	shootingAnimation();
        }
        if (key == 'g') {
        	isBumping = true;
        }
    }
    
    @Override
    public void keyReleased(int keyCode, char key) {
    	if(key == 'g') {
    		isBumping = false;
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
        if (isShooting == true) {
        	isShooting();
        }
    }
    
    
    private void shootBullet() {
		if (getCurrentFrameIndex() == 0) {
			Bullet b = new Bullet(world, -5, 1);
	    	world.addGameObject(b, getX() - getWidth(), getY());
		}
    	if (getCurrentFrameIndex() == 1) {
    		Bullet b = new Bullet(world, 5, 0);
        	world.addGameObject(b, getX() + getWidth(), getY());
    	}   	
	}

	private void shootingAnimation() {
    	isShooting = true;
    	setCorrectShootingFrame();
    	animationStart = System.currentTimeMillis();
	}

	private void setCorrectShootingFrame() {
		if (getCurrentFrameIndex() == 0) {
    		setCurrentFrameIndex(2);
    	}
    	else {
    		setCurrentFrameIndex(3);
    	}
	}
	
	private void isShooting() {
		long currentTime = System.currentTimeMillis();
    	if (currentTime - animationStart >= animationTime) {
        	if (getCurrentFrameIndex() == 2) {
    			setCurrentFrameIndex(0);
    			isShooting = false;
        	}
        	if (getCurrentFrameIndex() == 3) {
    			setCurrentFrameIndex(1);
    			isShooting = false;
        	}
        }
	}
	
	public void bulletHit() {
		hitpoints--;
	}
	

	@Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof BoardsTile) {
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
            	if (isBumping == true) {
            		try { 
            			((Swordfish) go).playerHit(getCenterX());
            		} catch (TileNotFoundException e) {
            			e.printStackTrace();
            		}
            	}
            }		
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
	
	public float getHitpoints() {
		return hitpoints;
	}
	
	public void newRound() {
		hitpoints =  10;
	}

}