import java.util.Scanner;

public class Adventure {
    private String destination;
    private int totalLevel;
    private int currentLevel;
    private Creature player;
    private Creature enemy;
    private boolean runState;
    private Coin[] coins;
    public final int BONUS_CONSTANT = 3;

    public Adventure(){
    }

    public Adventure(int currentLevel, int totalLevel, String destination,Creature player, Creature enemy){
        this.setCurrentLevel(currentLevel);
        this.setTotalLevel(totalLevel);
        this.setDestination(destination);
        this.player = player;
        this.enemy = enemy;
        this.runState = true;
        generateCoins(totalLevel);
    }

    public Adventure(int current_level, int total_level, String destination,Creature player){
        this.setCurrentLevel(current_level);
        this.setTotalLevel(total_level);
        this.setDestination(destination);
        this.player = player;
        this.enemy = Creature.randomly_generate_creature();
        this.runState = true;
        generateCoins(totalLevel);
    }

    private void generateCoins(int totalLevel){
        this.coins = new Coin[totalLevel];
        for(int i=0;i<totalLevel;i++){
            if((i+1)%BONUS_CONSTANT == 0){
                coins[i] = new Coin(2,true);
                continue;
            }
            int dice = ((int)(Math.random()*10));
            coins[i] = new Coin(1,(dice<7)?true:false);
            //coins[i] = new Coin(1,false);

        }
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public void setTotalLevel(int total_level) {
        this.totalLevel = total_level;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int current_level) {
        this.currentLevel = current_level;
    }

    public Creature getPlayer() {
        return player;
    }

    public Creature getEnemy() {
        return enemy;
    }

    public void setEnemy(Creature enemy) {
        this.enemy = enemy;
    }

    public void setPlayer(Creature player) {
        this.player = player;
    }

    public boolean getRunState() {
        return runState;
    }

    public void setRunState(boolean runState) {
        this.runState = runState;
    }

    public Coin[] getCoins() {
        return coins;
    }

    public void nextLevel(){
        this.currentLevel++;
    }

    public String toString(){
        String info =  player.getSpecies()+"'s "+"Adventure to "+destination+" with a "+enemy.getSpecies();
        //info = info + "\ncurrent level: "+ current_level+"\t\ttotal level: "+total_level;
        info = info + "\nplayer life: "+player.getLife()+"\t\t\tplayer gold: "+player.getWallet().getTotalValue();
        info = info + "\nenemy life: "+enemy.getLife()+"\t\t\tenemy gold: "+enemy.getWallet().getTotalValue();
        return info;
    }


    public void runAdventure(){
        Level level = new Level(this.currentLevel,this.totalLevel,this.destination,this.player,this.enemy);
//        for(int i=0;i<this.coins.length;i++){
//            System.out.println("coin1 : "+(this.coins[i].isReal()?"real":"fake")+"\tface value : "+coins[i].getFaceValue());
//        }
        Store store = new Store();
        while (level.runLevel(this.coins,store)) {
//            Main.print_a_line();
            System.out.println(level.toString());
            level.nextLevel();
            if(level.getCurrentLevel()<=level.getTotalLevel())System.out.println("进入Level "+level.getCurrentLevel());
        }
        Main.print_a_line();
        level.printEnding(this.coins);
    }
}
