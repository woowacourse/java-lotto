package lotto.model.lotto;

public class ManualCount {
    public static final int END = 0;
    private int number;

    public ManualCount(int number) {
        this.number = number;
    }

    public void createManualLotto() {
        this.number--;
    }

    public boolean isEnd() {
        return number == END;
    }

    public int getNumber() {
        return number;
    }
}
