public class Elf extends Creature{
    public Elf(){
        super(7,"Elf");
        this.setWinRate(8);
    }

    @Override
    public int calculateWinRate(Creature enemy) {
        return getWinRate();
    }

    @Override
    public boolean fightOrNot(Creature enemy) {
        return true;
    }
}
