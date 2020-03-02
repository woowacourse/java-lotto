package lotto.generator;

import lotto.domain.BettingInfo;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoGenerator implements LottoGenerator {

    @Override
    public Lottos createLottos(BettingInfo bettingInfo) {
        int lottoSize = bettingInfo.getAutoLottoSize();
        List<Lotto> lottos = new ArrayList<>();
        List<LottoNumber> lottoNumbers = new ArrayList(LottoNumber.lottoNumbers.values());
        for (int i = 0; i < lottoSize; i++) {
            Collections.shuffle(lottoNumbers);
            lottos.add(new Lotto(lottoNumbers.subList(0, Lotto.LOTTO_NUMBERS_SIZE)));
        }
        return new Lottos(lottos);
    }
}
