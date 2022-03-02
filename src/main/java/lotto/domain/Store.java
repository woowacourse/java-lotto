package lotto.domain;

import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private LottoMoney leftMoney;

    public Store(LottoMoney leftMoney) {
        this.leftMoney = leftMoney;
    }

    public List<Lotto> buyLottos(List<Lotto> manualLottos) {
        validateEnoughMoney(manualLottos.size());
        List<Lotto> lottos = buyManualLottos(manualLottos);
        lottos.addAll(buyAutomaticLottos());
        return lottos;
    }

    public void validateEnoughMoney(int manualLottoCount) {
        if (LottoMoney.createLottoMoneyByCount(manualLottoCount).isGreaterThan(leftMoney)) {
            throw new IllegalArgumentException("로또를 구매할 돈이 부족하다.");
        }
    }

    private List<Lotto> buyManualLottos(List<Lotto> manualLottos) {
        leftMoney = leftMoney.minus(LottoMoney.createLottoMoneyByCount(manualLottos.size()));
        return new ArrayList<>(manualLottos);
    }

    private List<Lotto> buyAutomaticLottos() {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy()) {
            lottos.add(buy());
        }
        return lottos;
    }

    private boolean canBuy() {
        return leftMoney.isGreaterThan(LottoMoney.createMinimumLottoMoney());
    }

    private Lotto buy() {
        leftMoney = leftMoney.minus(LottoMoney.createMinimumLottoMoney());
        return LottoGenerator.generate();
    }

}
