package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static LottoTickets generateTickets(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money.getNumberOfLotto(); i++) {
            lottos.add(RandomLottoGenerator.generate());
        }
        return new LottoTickets(lottos);
    }

    public static LottoTickets generateTickets(Money money, List<Lotto> lottos) {
        int numberOfRandomLotto = money.getNumberOfLotto() - lottos.size();
        for (int i = 0; i < numberOfRandomLotto; i++) {
            lottos.add(RandomLottoGenerator.generate());
        }
        return new LottoTickets(lottos);
    }
}
