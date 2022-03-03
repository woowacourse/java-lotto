package model.money;

public class ManualLottoCount {

    private final int count;

    public ManualLottoCount(int count) {
        checkValidCount(count);
        this.count = count;
    }

    private void checkValidCount(int count) {
        checkCountLessThanMinimum(count);
    }

    private void checkCountLessThanMinimum(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 입력한 수동 구매 로또 개수로 음수가 입력됬습니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
