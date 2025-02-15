package domain;

import java.util.ArrayList;
import java.util.List;
import utils.NumbersGenerator;

public class LottoStore {

    private final NumbersGenerator numbersGenerator;
    private final Money money;

    public LottoStore(final NumbersGenerator numbersGenerator, final Money money) {
        this.numbersGenerator = numbersGenerator;
        this.money = money;
    }

    public List<Lotto> issueLottos() {
        final int count = money.getCount();
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(numbersGenerator.generate()));
        }
        return lottos;
    }

}
