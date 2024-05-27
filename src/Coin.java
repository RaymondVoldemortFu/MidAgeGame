public class Coin {
    private int faceValue;
    private boolean real;
    private int value;

    public Coin(int faceValue, boolean real){
        this.faceValue=faceValue;
        this.real=real;
        this.value=faceValue*(real?1:0);
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public boolean isReal() {
        return real;
    }

    public void setReal(boolean real) {
        this.real = real;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
