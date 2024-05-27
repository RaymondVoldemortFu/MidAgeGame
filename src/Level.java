public class Level extends Adventure{
    public Level(int current_level, int total_level, String destination,Creature player, Creature enemy){
        super(current_level,total_level,destination,player,enemy);
    }

    private boolean checkLevel(){
        return this.getTotalLevel() >= this.getCurrentLevel();
    }

    public boolean checkGame(){
        return checkLevel()&&this.getPlayer().isSurvival();
    }

    //重构战斗逻辑
    public void fight(Coin[] coins,int buff){
        //单独处理矮人的情况
        if(getEnemy().getSpecies().equals("Dwarf")){
            //当矮人不想战斗，玩家拿走金币
            if(!getEnemy().fightOrNot(getPlayer())){
                getPlayer().addCoin(coins[getCurrentLevel()-1]);
                return;
            }
        }
        //当双方都有战斗欲望
        if(getEnemy().fightOrNot(getPlayer())&&getPlayer().fightOrNot(getEnemy())){
            //战斗
            //如果enemy赢了
            if(getEnemy().fight(getPlayer())){
                getEnemy().addCoin(coins[getCurrentLevel()-1]);
                if(buff == 3){//如果有闪避符咒
                    getPlayer().setBuff(null);
                    return;//免除扣血
                }
                getPlayer().hurt();
            }
            //如果player赢了
            else{
                getPlayer().addCoin(coins[getCurrentLevel()-1]);
                if(buff == 2) getEnemy().hurt(3);
                else getEnemy().hurt();
                getPlayer().setBuff(null);
            }
            return;
        }
        //当enemy没有战斗欲望
        if(!getEnemy().fightOrNot(getPlayer())){
            if(getPlayer().fightOrNot(getEnemy())){
                getPlayer().addCoin(coins[getCurrentLevel()-1]);
                return;
            }
            if(!getPlayer().fightOrNot(getEnemy())){
                return;
            }
        }
        //当玩家没有战斗欲望而enemy有
        if(!getPlayer().fightOrNot(getEnemy())&&getEnemy().fightOrNot(getPlayer())){
            getEnemy().addCoin(coins[getCurrentLevel()-1]);
            return;
        }
    }

    public boolean runLevel(Coin[] coins,Store store){
        if (!checkGame()) return false;             //检查游戏状态
        int buff = getPlayer().checkBuff();         //检查玩家使用的物品

        if (getCurrentLevel()%BONUS_CONSTANT==0){   //检查是否是奖励关卡
            if(getPlayer().isSurvival()){
                getPlayer().addCoin(coins[getCurrentLevel()-1]);
            }
            if(getEnemy().isSurvival()) {
                getEnemy().addCoin(coins[getCurrentLevel()-1]);
            }
            //分完钱
            Main.print_a_line();
            getPlayer().bodyCheck();
            System.out.println(this.toString());
            if(getPlayer().isWound()) System.out.println("!!!!!受伤了!!!!!");
            getPlayer().clearBuff();
            store.shopping(getPlayer());        //进商城
            getPlayer().checkBuff();            //检查是否需要回血
            if(getPlayer().isWound()) getPlayer().hurt(1);  //受伤，扣血
            if(getPlayer().isSurvival()){
                getPlayer().addLife(2);
            }
            if(getEnemy().isSurvival()) {
                getEnemy().addLife(2);
            }
            return true;
        }


        if(getEnemy().isSurvival()) {
            fight(coins,buff);  //如果敌人还活着，则进入战斗
            getPlayer().bodyCheck();
            getPlayer().checkSurvival();
        }

        else {
            getPlayer().bodyCheck();
            if(getPlayer().isSurvival()) getPlayer().addCoin(coins[getCurrentLevel()-1]);
            getPlayer().checkSurvival();
        }

        Main.print_a_line();
        System.out.println(this.toString());
        if(getPlayer().isWound()) System.out.println("!!!!!受伤了!!!!!");
        if(getPlayer().isSurvival()) store.shopping(getPlayer());
        getPlayer().checkBuff();        //检查有没有生命药剂加血
        getPlayer().checkSurvival();
        getEnemy().checkSurvival();
        getPlayer().bodyCheck();        //检查伤势
        getPlayer().clearBuff();        //清除物品
        if(getPlayer().isWound()) getPlayer().hurt(1);  //受伤，扣血
        getPlayer().checkSurvival();    //检查还活着吗
        if(getCurrentLevel()!=1){
            if(getPlayer().isSurvival()) getPlayer().addOneLife();
            if(getEnemy().isSurvival()) getEnemy().addOneLife();
        }
        return true;
    }

    public boolean winOrNot(){
        if(this.getCurrentLevel()==(this.getTotalLevel()+1)&&this.getPlayer().isSurvival()) return true;
        else return false;
    }


    public void printEnding(Coin[] coins){
        System.out.println(winOrNot()?"You Win!":"Game Over!");
        System.out.println("destination :\t"+getDestination());
        String winner = winOrNot()?getPlayer().getSpecies()+"(player)":getEnemy().getSpecies()+"(enemy)";
        System.out.println("winner : "+winner);
        System.out.println("player gold : "+getPlayer().getWallet().getTotalValue());
        System.out.println("enemy gold : "+getEnemy().getWallet().getTotalValue());
        System.out.println("player score : "+calculateScore(coins));
    }

    private int totalScore(Coin[] coins){
        int total = 0;
        for(int i=0;i<coins.length;i++){
            total = total + coins[i].getValue();
        }
        return total;
    }

    public int calculateScore(Coin[] coins){
        //return (int)((((double)(this.getPlayer().getWallet().getTotalValue()))/((double)(this.getPlayer().getWallet().getTotalValue()+this.getEnemy().getWallet().getTotalValue())))*100);
        return (int)((((double)(this.getPlayer().getWallet().getTotalValue()))/(double)(totalScore(coins)+(getTotalLevel()/BONUS_CONSTANT)*2))*100);
    }

    public String toString(){
        String info = "Level: "+getCurrentLevel()+"/"+getTotalLevel()+"\n";
        info = info + getPlayer().getSpecies()+"(player)"+"\t\tlife : "+getPlayer().getLife()+"\t\tgold : "+getPlayer().getWallet().getTotalValue()+"\n";
        info = info +getEnemy().getSpecies()+"(enemy)"+"\t\tlife : "+getEnemy().getLife()+"\t\tgold : "+getEnemy().getWallet().getTotalValue()+"\n";
        return info;
    }

}
