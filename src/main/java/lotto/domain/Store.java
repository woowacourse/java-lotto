package lotto.domain;

import lotto.domain.vo.LottoMoney;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private LottoMoney leftMoney;

    public Store(LottoMoney leftMoney) {
        this.leftMoney = leftMoney;
    }

    public List<Lotto> buyLottos(int manualLottoCount) {
        validateManualLottoCount(manualLottoCount);
        List<Lotto> manualLottos = buyManualLottos(manualLottoCount);
        List<Lotto> automaticLottos = buyAutomaticLottos();
        manualLottos.addAll(automaticLottos);
        return manualLottos;
    }

    private void validateManualLottoCount(int manualLottoCount) {
        if (LottoMoney.createLottoMoneyByCount(manualLottoCount).isGreaterThan(leftMoney)) {
            throw new IllegalArgumentException("로또를 구매할 돈이 부족하다.");
        }
    }

    private List<Lotto> buyManualLottos(int manualLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            String input = InputView.input();
            InputView.validateBlank(input);
            lottos.add(new Lotto(InputView.convertToNumbers(input)));
        }
        leftMoney = leftMoney.minus(LottoMoney.createLottoMoneyByCount(manualLottoCount));
        return lottos;
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
