package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.InvalidException;

public class Lottos {

    private static final int BASIC_LOTTO_MONEY = 1000;
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(final int money) {
        checkDivideMoney(money);
        LottoNumber numbers = new LottoNumber();
        int count = money / BASIC_LOTTO_MONEY;

        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(numbers.createLottoNumbers()));
        }
    }

    private static void checkDivideMoney(final int money) {
        if (!(money >= BASIC_LOTTO_MONEY && money % BASIC_LOTTO_MONEY == 0)) {
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_MONEY);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
