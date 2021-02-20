package lottogame.domain;

import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;
import lottogame.utils.CannotBuyLottoException;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    public int purchaseQuantity(Money money) {
        int quantity = money.buyLotto(LOTTO_PRICE);
        if (quantity == 0) {
            throw new CannotBuyLottoException();
        }
        return quantity;
    }

    public List<Lotto> buyLotto(int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottos.add(makeLotto());
        }
    }

    private Lotto makeLotto() {
        return new Lotto(new LottoNumber());
    }
}
