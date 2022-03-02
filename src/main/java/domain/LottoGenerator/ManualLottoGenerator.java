package domain.LottoGenerator;

import domain.Lotto.Lotto;

public class ManualLottoGenerator implements LottoGenerator{

    private static final String AUTO_LOTTO_GENERATE_LIMIT = "수동 로또 생성기에서는 자동 로또 생성을 할 수 없습니다.";

    @Override
    public Lotto generateLotto() {
        throw new IllegalArgumentException(AUTO_LOTTO_GENERATE_LIMIT);
    }
}
