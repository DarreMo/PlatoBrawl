package nl.han.ica.PlatoBrawl;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.PlatoBrawl.tiles.BoardTiles;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timon on 29-3-2018.
 * Edited by: Jeffrey & Timon
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {
	
	private boolean gotHitpointsUp;
	private boolean gotBulletUp;
	private boolean isShooting;
	private long animationStart;
    protected float hitpoints;
	private final int animationTime = 100;
    private final int size = 25;
    private final float gravity = 0.05f;
    private final PlatoBrawl world;
    private PlayerBullet b;
    private SuperBullet sb;
    private ArrayList<Bullet> bulletList = new ArrayList<>();


    public Player(PlatoBrawl world) {
        super(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/Dummy.png"), 4);
        this.world = world;
        setCurrentFrameIndex(1);
        setFriction(0.05f);
        setGravity(gravity);
        this.hitpoints = 10;
    }

    /**
	 * Player movement with arrowkeys
    */

    @Override
    public void keyPressed(int keyCode, char key) {
        int speed = 5;
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
    }
    
    
    /**
     * De Speler kan niet het scherm uit en sterft bij 0 hitpoints
     */
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
        if (hitpoints <= 0) {
        	newGame();
        }
    }

    /**
     * Schietmethode met 'Player' of 'Super' Bullet
     */    
    private void shootBullet() {
    	if (!gotBulletUp) {
			if (getCurrentFrameIndex() == 0) {
				b = new PlayerBullet(this, world, -5, 1);
		    	world.addGameObject(b, getX() - getWidth(), getY());
		    	bulletList.add(b);
			}
	    	if (getCurrentFrameIndex() == 1) {
	    		b = new PlayerBullet(this, world, 5, 0);
	        	world.addGameObject(b, getX() + getWidth(), getY());
	        	bulletList.add(b);
	    	}  
    	}
    	if (gotBulletUp) {
			if (getCurrentFrameIndex() == 0) {
				sb = new SuperBullet(this, world, -5, 1);
		    	world.addGameObject(sb, getX() - getWidth(), getY());
		    	bulletList.add(sb);
			}
	    	if (getCurrentFrameIndex() == 1) {
	    		sb = new SuperBullet(this, world, 5, 0);
	        	world.addGameObject(sb, getX() + getWidth(), getY());
	        	bulletList.add(sb);
	    	}  
    	}
	}
    
    /**
     * Schietanimatie
     */
	private void shootingAnimation() {
    	isShooting = true;
    	setCorrectShootingFrame();
    	animationStart = System.currentTimeMillis();
	}
	
	/**
     * Zorgt ervoor dat de speler de correcte kant op kijkt
     */
	private void setCorrectShootingFrame() {
		if (getCurrentFrameIndex() == 0) {
    		setCurrentFrameIndex(2);
    	}
    	else {
    		setCurrentFrameIndex(3);
    	}
	}
	
	/**
     * Zet de speler in de goede positie nadat de schietanimatie is afgelopen
     */
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
	
	/**
     * De speler wordt geraakt door een kogel en verliest levens
     */
	public void bulletHit() {
		hitpoints--;
	}
	
	/**
     * Nieuwe ronde
     */	
	public void newRound() {
		hitpoints =  10;
		if (gotHitpointsUp) {
			hitpoints = 20;
		}
		setSpeed(0);
		setX(900);
		setY(400);
		deleteBullets();
	}

	/**
     * Verwijder kogels bij nieuwe ronde
     */
	private void deleteBullets() {
		for (Bullet b : bulletList) {
			world.deleteGameObject(b);
		}
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
        	if (go instanceof Enemy) {
            	try {
            		if (go.getCenterX() >= 440 && go.getCenterX() <= 840) {
            			setX(150);
            			setY(world.getHeight()/2);
            			hitpoints-=5;
            		}
            		else {
            			setX(world.getWidth()/2);
            			setY(world.getHeight()/2);
            			hitpoints-=5;
            		}
            		} catch (TileNotFoundException e) {
            			e.printStackTrace();
            		}
            }	
        	if (go instanceof HitpointsUp) {
            	try { 
            		this.hitpoints = 20;
            		gotHitpointsUp = true;
           		} catch (TileNotFoundException e) {
           			e.printStackTrace();
           		}
            }
        	if (go instanceof BulletUp) {
            	try { 
            		gotBulletUp = true;
           		} catch (TileNotFoundException e) {
           			e.printStackTrace();
           		}
            }
        }	
    }
     
    /**
     * get Hitpoints
     */
	public float getHitpoints() {
		return hitpoints;
	}

	/**
     * De speler is dood en een nieuwe Game begint wanneer hij op 'r' drukt
     * @param world Referentie naar de wereld
     */	
	private void newGame() {
		world.round = 0;
		world.deleteAllGameOBjects();
		world.deleteAllDashboards();
		RestartButton rb = new RestartButton(world);
		world.addGameObject(rb, (world.getWidth()/2 - rb.getWidth()/2), (world.getHeight()/2 - rb.getHeight()/2));
		
	}

}