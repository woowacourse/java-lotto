package lotto.domain;

import lotto.domain.exceptions.InvalidNumOfCustomLottosException;

public class LottoCount {
    private static final int MIN_COUNT = 0;
    private final int numOfRandomLottos;
    private final int numOfCustomLottos;

    public LottoCount(LottoBuyingMoney lottoBuyingMoney, int numOfCustomLottos) {
        if (numOfCustomLottos > lottoBuyingMoney.numOfLottos() || numOfCustomLottos < MIN_COUNT) {
            throw new InvalidNumOfCustomLottosException("수동 로또 구매 수는 최소 " + MIN_COUNT
                    + "이상이어야 하고 구매 가능한 로또 수보다 작아야 합니다.");
        }
        this.numOfRandomLottos = lottoBuyingMoney.numOfLottos() - numOfCustomLottos;
        this.numOfCustomLottos = numOfCustomLottos;
    }

    public int custom() {
        return numOfCustomLottos;
    }

    public int random() {
        return numOfRandomLottos;
    }
}