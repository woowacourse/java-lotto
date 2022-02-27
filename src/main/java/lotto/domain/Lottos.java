package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.InvalidException;

public class Lottos {

    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(final int money) {
        checkDivideMoney(money);
        int count = money / Money.getBasicLottoMoney();

        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoNumber.createLottoNumbers()));
        }
    }

    private static void checkDivideMoney(final int money) {
        if (!(money >= Money.getBasicLottoMoney() && money % Money.getBasicLottoMoney() == 0)) {
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_MONEY);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
