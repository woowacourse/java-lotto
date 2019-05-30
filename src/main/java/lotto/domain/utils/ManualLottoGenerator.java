package lotto.domain.utils;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;

import java.util.List;

public class ManualLottoGenerator {

    public static Lotto makeLotto(List<Number> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }
}
