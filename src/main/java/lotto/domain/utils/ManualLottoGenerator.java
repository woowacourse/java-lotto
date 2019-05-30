package lotto.domain.utils;

import lotto.domain.Lotto;
import lotto.domain.Number;
import java.util.List;

public class ManualLottoGenerator {

    public static Lotto makeLotto(List<Number> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }
}
