public class EvasionCharm extends Goods implements Item{
    public final int ID = 3;
    public EvasionCharm(){
        super("EvasionCharm",3);
    }

    @Override
    public void effect(Creature creature) {

    }

    public int getID() {
        return ID;
    }
}
