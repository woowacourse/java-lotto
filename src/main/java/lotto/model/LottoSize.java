package lotto.model;

public class LottoSize {
    private final int size;

    public LottoSize(int size) {
        validateLottoSize(size);
        this.size = size;
    }

    public int getRestOfLottoSize(int availableSize) {
        int restSize = availableSize - size;
        validateLottoSize(restSize);
        return restSize;
    }

    private void validateLottoSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("로또는 0장 이상 구매하셔야 합니다.");
        }
    }

    public void validateLottoSizeWithMoney(Money money) {
        if (money.getBuyableLottoSize() < size) {
            throw new IllegalArgumentException("수동 로또의 수가 구입 금액을 넘을 수 없습니다.");
        }
    }

    public int getSize() {
        return size;
    }
}
