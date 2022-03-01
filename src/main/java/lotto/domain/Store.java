package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private static final int LOTTO_PRICE = 1_000;
    private static final int MINIMUM_LOTTO_AMOUNT = 0;

    private int leftMoney;

    public Store(int money) {
        validateUnderLimit(money);
        validateUnderThousands(money);
        this.leftMoney = money;
    }

    public void buyManualLottos(int manualLottoAmount) {
        validateOverAmount(manualLottoAmount);
        leftMoney -= LOTTO_PRICE * manualLottoAmount;
    }

    public List<Lotto> buyAutoLottos() {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy(LOTTO_PRICE)) {
            leftMoney -= LOTTO_PRICE;
            lottos.add(LottoMachine.generate());
        }
        return lottos;
    }

    public boolean isBuyManualLotto(int amount) {
        return amount > MINIMUM_LOTTO_AMOUNT;
    }

    private boolean canBuy(int price) {
        return leftMoney >= price;
    }

    private void validateUnderLimit(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("입력금액은 1,000원 이상이어야 한다.");
        }
    }

    private void validateUnderThousands(int money) {
        if (money % LOTTO_PRICE != MINIMUM_LOTTO_AMOUNT) {
            throw new IllegalArgumentException("입력금액은 1,000원 단위어야 한다.");
        }
    }

    private void validateOverAmount(int manualLottoAmount) {
        if (!canBuy(LOTTO_PRICE * manualLottoAmount)) {
            throw new IllegalArgumentException("최대 구매 개수를 초과할 수 없다.");
        }
    }
}
