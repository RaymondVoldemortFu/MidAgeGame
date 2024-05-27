public interface Strategy {
    int calculateWinRate(Creature enemy);
    boolean fightOrNot(Creature enemy);
    boolean fight(Creature enemy);
}
