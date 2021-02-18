package lotto.utils;

import java.util.Arrays;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;

public class FixedLottoGenerator implements LottoGenerator {

    @Override
    public LottoTicket generateLottoTicket() {
        return new LottoTicket(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
    }
}
