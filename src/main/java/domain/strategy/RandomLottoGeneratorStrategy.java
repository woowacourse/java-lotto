package domain.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoPurchaseInfo;

public class RandomLottoGeneratorStrategy implements LottoGeneratorStrategy {

    private List<LottoNumber> lottoNumbers;

    @Override
    public List<Lotto> generate(LottoPurchaseInfo lottoPurchaseInfo) {
        lottoNumbers = new ArrayList<>(LottoNumber.getLottoNumbers());

        final List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoPurchaseInfo.getAutomaticCount(); i++) {
            Collections.shuffle(lottoNumbers);
            lottos.add(Lotto.fromLotto(lottoNumbers));
        }

        return lottos;
    }
}
