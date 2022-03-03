package model.money;

public class ManualLottoCount {

    private final int count;

    public ManualLottoCount(int count, int purchaseLottoCount) {
        checkValidCount(count, purchaseLottoCount);
        this.count = count;
    }

    private void checkValidCount(int count, int purchaseLottoCount) {
        checkCountLessThanMinimum(count);
        checkCountOverThanMaximum(count, purchaseLottoCount);
    }

    private void checkCountLessThanMinimum(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 입력한 수동 구매 로또 개수가 최소로 구매할 개수보다 작습니다.");
        }
    }
    
    private void checkCountOverThanMaximum(int count, int purchaseLottoCount) {
        if (count > purchaseLottoCount) {
            throw new IllegalArgumentException("[ERROR] 입력한 수동 구매 로또 개수가 최대로 구매할 수 있는 개수보다 많습니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
