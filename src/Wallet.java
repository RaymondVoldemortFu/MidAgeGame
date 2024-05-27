import java.util.ArrayList;
//钱包
public class Wallet {
    private ArrayList<Coin> oneValueCoin;
    private ArrayList<Coin> twoValueCoin;
    private ArrayList<Coin> fakeCoin;
    private int totalValue;

    public Wallet(){
        this.totalValue=0;
        this.oneValueCoin=new ArrayList<Coin>();
        this.twoValueCoin=new ArrayList<Coin>();
        this.fakeCoin=new ArrayList<Coin>();
    }

    public void updateTotalValue(){
        this.totalValue=0;
        for(int i=0;i<oneValueCoin.size();i++){
            this.totalValue = this.totalValue + oneValueCoin.get(i).getValue();
        }
        for(int i=0;i<twoValueCoin.size();i++){
            this.totalValue = this.totalValue + twoValueCoin.get(i).getValue();
        }
    }

    public void printWallet(){
        System.out.println("one value coin: ");
        for(int i=0;i<oneValueCoin.size();i++){
            System.out.println("face value : "+oneValueCoin.get(i).getFaceValue()+"\treal? "+oneValueCoin.get(i).isReal()+"\tvalue :"+oneValueCoin.get(i).getValue());
        }
        System.out.println("twp value coin: ");
        for(int i=0;i<twoValueCoin.size();i++){
            System.out.println("face value : "+twoValueCoin.get(i).getFaceValue()+"\treal? "+twoValueCoin.get(i).isReal()+"\tvalue :"+twoValueCoin.get(i).getValue());
        }
        System.out.println("fake coin : ");
        for(int i=0;i<fakeCoin.size();i++){
            System.out.println("face value : "+fakeCoin.get(i).getFaceValue()+"\treal? "+fakeCoin.get(i).isReal()+"\tvalue :"+fakeCoin.get(i).getValue());
        }
    }

    public ArrayList<Coin> getFakeCoin() {
        return fakeCoin;
    }

    public ArrayList<Coin> getOneValueCoin() {
        return oneValueCoin;
    }

    public ArrayList<Coin> getTwoValueCoin() {
        return twoValueCoin;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void addCoin(Coin coin){
        //System.out.println("coin: "+coin.isReal()+"\tfacevalue "+coin.getFaceValue()+"\tvalue"+coin.getValue());
        if(!coin.isReal()){
            fakeCoin.add(coin);
            return;
        }
        else if(coin.isReal()){
            if(coin.getValue()==1) {
                oneValueCoin.add(coin);
            }
            else if(coin.getValue()==2) twoValueCoin.add(coin);
            updateTotalValue();
        }
    }

    public boolean pay(int money, Wallet dest){
        if(totalValue<money) return false;
        int temp = money;
        while (temp>0){
            //优先花面额为2的金币
            while (temp>=2&&(!twoValueCoin.isEmpty())){
                temp = temp - 2;
                dest.addCoin(twoValueCoin.getLast());
                twoValueCoin.removeLast();
            }
            //假如画完之后还有剩的，用面额为1的金币
            if(temp>0&&(!oneValueCoin.isEmpty())){
                temp=temp-1;
                dest.addCoin(oneValueCoin.getLast());
                oneValueCoin.removeLast();
            }
            //如果还剩1金币，但只有面额为2的金币，让奸商找零，默认奸商找的开
            if(temp==1 && (oneValueCoin.isEmpty()) &&(!twoValueCoin.isEmpty())){
                temp = temp-1;
                dest.addCoin(twoValueCoin.getLast());
                twoValueCoin.removeLast();
                oneValueCoin.add(dest.oneValueCoin.getLast());
                dest.oneValueCoin.removeLast();
            }
        }
        updateTotalValue();
        return true;
    }
}
