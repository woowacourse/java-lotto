package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTicketTest {
    @Test
    void 번호_갯수_오류() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        });
    }

    @Test
    void 같은번호_갯수() {
        List<LottoNumber> lottoNumbersFirst = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)
        );

        List<LottoNumber> lottoNumbersSecond = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(7)
        );

        LottoTicket lottoTicket1 = new LottoTicket(lottoNumbersFirst);
        LottoTicket lottoTicket2 = new LottoTicket(lottoNumbersSecond);
        assertThat(lottoTicket1.getSameCount(lottoTicket2)).isEqualTo(5);
    }
}