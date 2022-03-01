package lotto.domain;

import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Store {

    private Money leftMoney;

    public Store(Money leftMoney) {
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
        if (Money.createLottoMoneyByCount(manualLottoCount).isGreaterThan(leftMoney)) {
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
        leftMoney = leftMoney.minus(Money.createLottoMoneyByCount(manualLottoCount));
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
        return leftMoney.isGreaterThan(Money.createMinimumLottoMoney());
    }

    private Lotto buy() {
        leftMoney = leftMoney.minus(Money.createMinimumLottoMoney());
        return LottoGenerator.generate();
    }
}
