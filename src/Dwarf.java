public class Dwarf extends Creature{
    public Dwarf(){
        super(9,"Dwarf");
        this.setWinRate(5);
    }

    @Override
    public int calculateWinRate(Creature enemy) {
        return getWinRate();
    }

    @Override
    public boolean fightOrNot(Creature enemy) {
        return getLife()>2;
    }
}
