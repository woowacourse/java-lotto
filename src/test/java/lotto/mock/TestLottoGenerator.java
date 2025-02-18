package lotto.mock;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;

public class TestLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        return new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }
}
