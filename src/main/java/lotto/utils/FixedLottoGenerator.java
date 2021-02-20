package lotto.utils;

import java.util.Arrays;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;

public class FixedLottoGenerator implements LottoGenerator {

    private static final LottoTicket LOTTO_TICKET = new LottoTicket(Arrays.asList(
            LottoNumber.valueOf("1"),
            LottoNumber.valueOf("2"),
            LottoNumber.valueOf("3"),
            LottoNumber.valueOf("4"),
            LottoNumber.valueOf("5"),
            LottoNumber.valueOf("6")
    ));

    @Override
    public LottoTicket generateLottoTicket() {
        return LOTTO_TICKET;
    }
}
