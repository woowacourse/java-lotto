package lotto.service;


import java.util.ArrayList;
import java.util.List;
import lotto.domain.AmountPaid;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.utils.NumberGenerator;

public class LottoService {

    public LottoBundle makeLottoBundle(AmountPaid amount) {
        List<Lotto> lottos = new ArrayList<>();
        for(int i =0; i<amount.getLottoQuantity(); i++) {
            lottos.add(makeLotto());
        }
        return new LottoBundle(lottos);
    }

    private Lotto makeLotto() {
        return new Lotto(NumberGenerator.numberGeneratorWithUniqueValues(6,1,45));
    }
}
