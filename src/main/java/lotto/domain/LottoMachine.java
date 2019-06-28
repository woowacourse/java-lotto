package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static LottoTickets generateTickets(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        return generate(lottos, money.getNumberOfLotto());
    }

    public static LottoTickets generateTickets(Money money, List<Lotto> lottos) {
        int numberOfRandomLotto = money.getNumberOfLotto() - lottos.size();
        return generate(lottos, numberOfRandomLotto);
    }

    private static LottoTickets generate(List<Lotto> lottos, int numberOfLotto) {
        for (int i = 0; i < numberOfLotto; i++) {
            lottos.add(RandomLottoGenerator.generate());
        }
        return new LottoTickets(lottos);
    }
}
