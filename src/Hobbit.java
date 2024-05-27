public class Hobbit extends Creature {

    public Hobbit(){
        super(5 + (((int)(Math.random()*10))%5),"Hobbit");
    }

    @Override
    public boolean fightOrNot(Creature enemy) {
        return getLife()>=2;
    }
}
