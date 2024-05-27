public class Orc extends Creature{
    public Orc(){
        super(8,"Orc");
    }

    public int calculateWinRate(Creature enemy) {
        if(this.getLife()>enemy.getLife()){
            setWinRate(6);
            return 6;
        }
        if(this.getLife()==enemy.getLife()){
            setWinRate(4);
            return 4;
        }
        //if(this.getLife()<enemy.getLife()){
        else {
            setWinRate(3);
            return 3;
        }
    }

    @Override
    public boolean fightOrNot(Creature enemy) {
        return true;
    }
}
