package lotto.domain;

import java.util.Set;

public class LottoFactory {

    private LottoFactory() {
    }

    public static Lotto makeLotto(Set<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static void createManualLottos(Lottos lottos, Set<LottoNumber> lottoNumbers) {
        lottos.add(makeLotto(lottoNumbers));
    }

    public static void createAutoLottos(Lottos lottos, int counts) {
        for (int i = 0; i < counts; i++) {
            lottos.add(RandomNumberGenerator.makeTicket());
        }
    }
}
