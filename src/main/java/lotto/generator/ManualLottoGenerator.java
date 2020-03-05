package lotto.generator;

import lotto.domain.BettingInfo;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ManualLottoGenerator implements LottoGenerator {
    @Override
    public Lottos createLottos(BettingInfo bettingInfo) {
        int lottoSize = bettingInfo.getManualLottoSize();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoSize; i++) {
            List<String> lottoNumbersInput = bettingInfo.getManualLottoNumbers(i);
            lottos.add(createLotto(lottoNumbersInput));
        }
        return new Lottos(lottos);
    }

    public static Lotto createLotto(List<String> lottoNumbersInput) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        for (String lottoNumberInput : lottoNumbersInput) {
            lottoNumbers.add(LottoNumber.valueOf(lottoNumberInput));
        }
        return new Lotto(lottoNumbers);
    }
}
