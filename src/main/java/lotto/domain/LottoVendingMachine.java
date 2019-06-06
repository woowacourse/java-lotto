package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {
    public static LottoTickets purchase(Money money, LottoGenerators lottoManualGenerators) {
        int lottoAutoCount = money.calCountOfLotto() - lottoManualGenerators.size();
        for (int position = 0; position < lottoAutoCount; position++) {
            lottoManualGenerators.add(new LottoAutoGenerator());
        }
        return createLottoTickets(lottoManualGenerators);
    }

    private static LottoTickets createLottoTickets(LottoGenerators lottoGenerators) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = 0; index < lottoGenerators.size(); index++) {
            lottos.add(Lotto.of(lottoGenerators.get(index)));
        }
        return new Lottos(lottos);
    }
}
