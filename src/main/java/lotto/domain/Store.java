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
        List<Lotto> lottos = buyManualLottos(manualLottos);
        lottos.addAll(buyAutomaticLottos());
        return lottos;
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
