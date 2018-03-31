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

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public Swordfish(PlatoBrawl world) {
        this(new Sprite("src/main/java/nl/han/ica/PlatoBrawl/media/sprites/swordfish.png"));
        this.world=world;
        this.hitpoints = 100;
    }

    /**
     * Maak een Swordfish aan met een sprite
     * @param sprite De sprite die aan dit object gekoppeld moet worden
     */
    private Swordfish(Sprite sprite) {
        super(sprite);
        setxSpeed(-1);
    }

    @Override
    public void update() {
        if (getX()+getWidth()<=0) {
            setX(world.getWidth());
        }
    }

    
    public int getHitpoints() {
    	return hitpoints;
    }
    
    public void swordfishHit() {
    	hitpoints--;
    }
    
    public void playerHit() {
    	hitpoints += 50;
    }
}
