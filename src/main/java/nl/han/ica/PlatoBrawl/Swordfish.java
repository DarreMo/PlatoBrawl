package nl.han.ica.PlatoBrawl;


import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * @author Ralph Niels
 * Een zwaardvis is een spelobject dat zelfstandig
 * door de wereld beweegt
 */
public class Swordfish extends SpriteObject {

    private PlatoBrawl world;
    private int hitpoints;
    private HealthBar healthbar;

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public Swordfish(PlatoBrawl world) {
        this(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/swordfish.png"));
        this.world=world;
        this.hitpoints = 10;
        setHealthBar();
    }

    /**
     * Maak een Swordfish aan met een sprite
     * @param sprite De sprite die aan dit object gekoppeld moet worden
     */
    private Swordfish(Sprite sprite) {
        super(sprite);
        setxSpeed(-1);
        int random = (int )(Math.random() * 4 + 1);
        setySpeed(random);
    }

    @Override
    public void update() {
        if (getX() + getWidth() <= 0) {
            setX(world.getWidth());
        }
        if (getY() + getHeight() <= 0) {
            setY(world.getHeight());
        }
        if (getY() >= world.getHeight()) {
            setY(0 - getHeight());
        }
        if (hitpoints <= 0) {
        	world.deleteGameObject(this);
        	world.deleteGameObject(healthbar);
        	world.numberOfSwordfish--;
        	if (world.numberOfSwordfish == 0) {
        		nextRound();
        	}
        }
    }
  
    
    private void setHealthBar() {
    	HealthBar h = new HealthBar(this);
        world.addGameObject(h);
        this.healthbar = h;
    }
    
    private void nextRound() {
    	world.round++;
    	world.numberOfSwordfish = world.round;
    	for (int i = 0; i < world.numberOfSwordfish; i++) {
    		Swordfish swordfish = new Swordfish(world);
    		int random = (int )(Math.random() * 200 + 100);
    		world.addGameObject(swordfish, random, random);
    	}
    }
    

    public int getHitpoints() {
    	return hitpoints;
    }
    
    
    public void swordfishHit() {
    	hitpoints--;
    }
    
    public void playerHit() {
    	hitpoints = 10;
    }

}
