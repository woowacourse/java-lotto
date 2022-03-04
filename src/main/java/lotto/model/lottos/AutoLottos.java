package lotto.model.lottos;

import java.util.ArrayList;
import java.util.List;

import lotto.ValidationUtils;
import lotto.model.Lotto;
import lotto.model.numbergenerator.LottoNumberGenerator;

public class AutoLottos extends Lottos {
    public AutoLottos(LottoNumberGenerator lottoNumberGenerator, int count) {
        super(lottoNumberGenerator, count);
        List<Lotto> autoLottos = new ArrayList<>(generateLottos(lottoNumberGenerator, count));
        ValidationUtils.validateEmptyCollection(autoLottos);
    }
}
