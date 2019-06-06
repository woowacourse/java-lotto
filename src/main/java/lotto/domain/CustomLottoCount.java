package lotto.domain;

import lotto.domain.exception.InvalidCustomCountException;

public class CustomLottoCount {
    private final int count;

    public CustomLottoCount(Money money, int count) {
        validCountOfCustomLotto(money, count);
        this.count = count;
    }

    private static void validCountOfCustomLotto(Money money, int countOfCustomLottos) {
        checkMaximum(money, countOfCustomLottos);
        checkMinimum(countOfCustomLottos);
    }

    private static void checkMaximum(Money money, int countOfCustomLottos) {
        if (money.howManyLotto() - countOfCustomLottos < 0) {
            throw new InvalidCustomCountException("구매 금액보다 많은 로또는 구매가 불가능합니다.");
        }
    }

    private static void checkMinimum(int countOfCustomLottos) {
        if (countOfCustomLottos < 0) {
            throw new InvalidCustomCountException("0장 보다 작을순 없습니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
