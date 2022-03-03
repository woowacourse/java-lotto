package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.view.InputView;

public class Store {

    public static final int MINIMUM_AMOUNT = 0;

    public List<Lotto> buyManualLotto(int amount) {
        validateAmount(amount);
        return InputView.inputManualLottoNumbers(amount);
    }

    public List<Lotto> buyAutoLotto(int amount) {
        validateAmount(amount);
        return IntStream.range(0, amount)
            .mapToObj(i -> LottoMachine.generate())
            .collect(Collectors.toList());
    }

    private void validateAmount(int amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("구매 갯수는 음수일 수 없다.");
        }
    }
}
