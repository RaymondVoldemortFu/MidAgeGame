//生物类

public class Creature implements Strategy{
    private boolean survival;       //标记生物是否存活
    private int life;               //储存生命值
    private String species;         //储存物种
    //private int gold;               //储存黄金数量
    private Wallet wallet;
    private Goods buff;
    private int winRate;
    private boolean wound;


    public Creature(){
        this.survival = true;
    }

    public Creature(int life, String species){
        this.survival = true;
        this.life = life;
        this.species = species;
        //this.gold = gold;
        this.wallet = new Wallet();
        this.wound = false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isSurvival() {
        return survival;
    }

    public void setSurvival(boolean survival) {
        this.survival = survival;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void addCoin(Coin coin){
        this.wallet.addCoin(coin);
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    //    public int getGold() {
//        return gold;
//    }

//    public void setGold(int gold) {
//        this.gold = gold;
//    }


    public void setBuff(Goods buff) {
        this.buff = buff;
    }

    public Goods getBuff() {
        return buff;
    }

    public boolean isWound() {
        return wound;
    }

    public void setWound(boolean wound) {
        this.wound = wound;
    }

    public void bodyCheck(){
        if(life<=2) this.wound = true;
        else this.wound = false;
    }

    public int checkBuff(){
        if(buff instanceof LifePotion){
            this.life = this.life + 2;
            this.buff = null;
            return 1;
        }
        if(buff instanceof RagePotion){
            return 2;
        }
        if(buff instanceof EvasionCharm){
            return 3;
        }
        return 0;
    }

    public void clearBuff(){
        this.buff = null;
    }

    public void addOneLife(){
        this.life++;
    }

    public void addLife(int num){
        this.life=this.life + num;
    }

//    public void addOneGold(){
//        this.gold++;
//    }

    public void hurt(){
        this.life=this.life-2;
    }

    public void hurt(int num){
        this.life = this.life -num;
    }

    public void checkSurvival(){
        if(this.life<=0) this.survival = false;
    }

    public static Creature randomly_generate_creature(){
        int index =(((int)(Math.random()*10))%3);
        switch (index){
            case 0:{
                return new Dwarf();
            }
            case 1:{
                return new Elf();
            }
            case 2:{
                return new Orc();
            }
            default:{
                return new Creature();
            }
        }
    }

    @Override
    public int calculateWinRate(Creature enemy) {
        return winRate;
    }

    @Override
    public boolean fightOrNot(Creature enemy) {
        return false;
    }

    @Override
    public boolean fight(Creature enemy) {
        int winRate_temp = calculateWinRate(enemy);
        int dice = ((int)(Math.random()*10));
        return dice<winRate_temp;
    }
}
