//package nl.han.ica.PlatoBrawl;
//
//import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
//import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
//import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
//import nl.han.ica.PlatoBrawl.PlatoBrawl;
//
///**
// * @author Ralph Niels
// * Een zwaardvis is een spelobject dat zelfstandig
// * door de wereld beweegt
// */
//public class Swordfish extends SpriteObject {
//
//    private PlatoBrawl world;
//
//    /**
//     * Constructor
//     * @param world Referentie naar de wereld
//     */
//    public Swordfish(PlatoBrawl world) {
//        this(new Sprite("src/main/java/nl/han/ica/platogrot/media/swordfish.png"));
//        this.world=world;
//    }
//
//    /**
//     * Maak een Swordfish aan met een sprite
//     * @param sprite De sprite die aan dit object gekoppeld moet worden
//     */
//    private Swordfish(Sprite sprite) {
//        super(sprite);
//        setxSpeed(-1);
//    }
//
//    @Override
//    public void update() {
//        if (getX()+getWidth()<=0) {
//            setX(world.getWidth());
//        }
//
//    }
//}
