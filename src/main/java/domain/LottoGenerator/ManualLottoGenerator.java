package domain.LottoGenerator;

import domain.Lotto.Lotto;
import utils.ExceptionMessage;

public class ManualLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto() {
        throw new IllegalArgumentException(ExceptionMessage.AUTO_LOTTO_GENERATE_LIMIT);
    }
}
