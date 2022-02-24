package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(int lottoCount, LottoNumberGenerator lottoNumberGenerator) {
        for (int i = 0; i < lottoCount; i++) {
            List<LottoNumber> lottoNumbers = lottoNumberGenerator.generate();
            lottos.add(new Lotto(lottoNumbers));
        }
    }

    public List<LottoReward> calculateLottoReward(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchCount = winningLotto.calculateSameLotto(lotto);
            boolean hasBonus = lotto.hasSameNumber(winningLotto.getBonusNumber());

            lottoRewards.add(LottoReward.find(matchCount, hasBonus));
        }

        return lottoRewards;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
