package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {
    public static LottoTickets purchase(Money money, LottoNoGenerators lottoNoManualGenerators) {
        int lottoAutoCount = money.calCountOfLotto() - lottoNoManualGenerators.size();
        for (int position = 0; position < lottoAutoCount; position++) {
            lottoNoManualGenerators.add(new LottoNoAutoGenerator());
        }
        return createLottoTickets(lottoNoManualGenerators);
    }

    private static LottoTickets createLottoTickets(LottoNoGenerators lottoNoGenerators) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = 0; index < lottoNoGenerators.size(); index++) {
            lottos.add(LottoFactory.create(lottoNoGenerators.get(index)));
        }
        return new Lottos(lottos);
    }
}
