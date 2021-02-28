package lotto.utils;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class FixedLottoGenerator implements LottoGenerator {
    @Override
    public LottoTicket generateLottoTicket() {
        return new LottoTicket(Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(6)
        ));
    }

}
