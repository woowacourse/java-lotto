package lotto.controller;

import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoMachine;
import lotto.view.InputView;

public enum LottosFactory {
    MANUAL(InputView::inputManualLottoNumbers),
    AUTO(LottoMachine::generateLottos);

    private static final int MINIMUM = 0;
    
    private final LottoGenerator lottoGenerator;

    LottosFactory(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> generate(int amount) {
        validationAmount(amount);
        if (amount == MINIMUM) {
            return Collections.emptyList();
        }
        return lottoGenerator.generateLottos(amount);
    }

    private void validationAmount(int amount) {
        if (amount < MINIMUM) {
            throw new IllegalArgumentException("로또의 개수는 음수일 수 없다.");
        }
    }
}
