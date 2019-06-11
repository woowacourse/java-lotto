package lotto.domain;

import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static LottoTickets getLottoTickets(Money money, int numberOfManualLotto) {
        if (numberOfManualLotto > 0) {
            List<Lotto> manualLotto = InputView.getManualLotto(numberOfManualLotto);
            return generateTickets(money, manualLotto);
        }
        return generateTickets(money);
    }

    static LottoTickets generateTickets(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money.getNumberOfLotto(); i++) {
            lottos.add(RandomLottoGenerator.generate());
        }
        return new LottoTickets(lottos);
    }

    static LottoTickets generateTickets(Money money, List<Lotto> manualLotto) {
        List<Lotto> lottos = manualLotto;
        int numberOfRandomLotto = money.getNumberOfLotto() - manualLotto.size();
        for (int i = 0; i < numberOfRandomLotto; i++) {
            lottos.add(RandomLottoGenerator.generate());
        }
        return new LottoTickets(lottos);
    }
}
