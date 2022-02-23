package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        for (int i = 0; i < lottoCount; i++) {
            List<LottoNumber> lottoNumbers = lottoNumberGenerator.generate();
            lottos.add(new Lotto(lottoNumbers));
        }
    }

    public List<LottoReward> calculateLottoReward(WinningLotto winningLotto, LottoNumber bonusBall) {
        List<LottoReward> lottoRewards = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchCount = winningLotto.calculateSameLotto(lotto);
            boolean hasBonus = lotto.hasSameNumber(bonusBall);

            lottoRewards.add(LottoReward.find(matchCount, hasBonus));
        }

        return lottoRewards;
    }
}
