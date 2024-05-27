import java.util.Scanner;

public class Store {
    public LifePotion lifePotion;
    public RagePotion ragePotion;
    public EvasionCharm evasionCharm;
    boolean LifePotionSold;
    boolean RagePotionSold;
    boolean EvasionCharmSold;
    private Wallet wallet;  //奸商的钱包

    public Store(){
        lifePotion = new LifePotion();
        ragePotion = new RagePotion();
        evasionCharm = new EvasionCharm();
        LifePotionSold = false;
        RagePotionSold = false;
        EvasionCharmSold = false;
        wallet = new Wallet();
        //给奸商加100个金币，防止找不出钱
        for(int i = 0;i<100;i++){
            wallet.addCoin(new Coin(1,true));
        }
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void shopping(Creature player){
        System.out.println("Welcome to the Store!");
        if(!LifePotionSold) System.out.println("ID : 1\t\t生命药剂\t\t2 gold\t\t恢复冒险者2点生命值");
        if(!RagePotionSold) System.out.println("ID : 2\t\t暴击药剂\t\t2 gold\t\t如果下一关卡发生了战斗并获胜,可以让对手生命值减少3");
        if(!EvasionCharmSold) System.out.println("ID : 3\t\t闪避符咒\t\t3 gold\t\t如果下一关卡发生了战斗并战败,可以免除生命值的减少");
        System.out.println("输入商品ID以购买药物，输入y进入下一关，输入n退出游戏");
        boolean buy = false;
        do {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("illegal input! 输入商品ID以购买药物，输入y进入下一关，输入n退出游戏");
                continue;
            }
            if(input.charAt(0)=='y'){
                break;
            }
            if(input.charAt(0)=='n'){
                System.exit(1);
            }
            switch (input.charAt(0)){
                case '1':{
                    if(buy){
                        System.out.println("一回合仅可购买一次");
                        continue;
                    }
                    if(LifePotionSold) {
                        System.out.println("无生命药剂，请重新输入");
                        continue;
                    }
                    else {
                        if(player.getWallet().pay(2,this.getWallet())){
                            player.setBuff(this.lifePotion);
                            System.out.println("购买成功");
                            LifePotionSold = true;
                            buy = true;
                            continue;
                        }
                        else {
                            System.out.println("余额不足");
                            continue;
                        }
                    }
                }
                case '2':{
                    if(buy){
                        System.out.println("一回合仅可购买一次");
                        continue;
                    }
                    if(RagePotionSold) {
                        System.out.println("无狂暴药剂，请重新输入");
                        continue;
                    }
                    else {
                        if(player.getWallet().pay(2,getWallet())){
                            player.setBuff(ragePotion);
                            System.out.println("购买成功");
                            RagePotionSold = true;
                            buy = true;
                            continue;
                        }
                        else {
                            System.out.println("余额不足");
                            continue;
                        }
                    }
                }
                case '3':{
                    if(buy){
                        System.out.println("一回合仅可购买一次");
                        continue;
                    }
                    if(EvasionCharmSold) {
                        System.out.println("无闪避符咒，请重新输入");
                        continue;
                    }
                    else {
                        if(player.getWallet().pay(3,getWallet())){
                            player.setBuff(evasionCharm);
                            System.out.println("购买成功");
                            EvasionCharmSold = true;
                            buy = true;
                            continue;
                        }
                        else {
                            System.out.println("余额不足");
                            continue;
                        }
                    }
                }
                default:{
                    System.out.println("illegal input! 输入商品ID以购买药物，输入y进入下一关，输入n退出游戏");
                    continue;
                }
            }
        } while (true);
    }

}
