public class RagePotion extends Goods implements Item{
    public final int ID = 2;
    public RagePotion(){
        super("RagePotion",2);
    }

    @Override
    public void effect(Creature creature) {

    }

    public int getID() {
        return ID;
    }
}
