public class LifePotion extends Goods implements Item{
    public final int ID = 1;
    public LifePotion(){
        super("LifePotion",2);
    }

    @Override
    public void effect(Creature creature) {

    }

    public int getID() {
        return ID;
    }
}
