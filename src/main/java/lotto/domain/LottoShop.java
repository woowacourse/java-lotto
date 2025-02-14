package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoShop(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Wallet buyLottos(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < money.getAmount(); i++) {
            List<Integer> numbers = lottoNumberGenerator.generate();
            lottos.add(new Lotto(numbers));
        }
        return new Wallet(lottos);
    }
}
