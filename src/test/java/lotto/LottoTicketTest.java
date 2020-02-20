package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {
    @Test
    void compare() {
        //given
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));

        LottoTicket winningLottoTicket = new LottoTicket(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(10),
                new LottoNumber(11),
                new LottoNumber(12)));
        LottoNumber bonusNumber = new LottoNumber(13);

        assertThat(lottoTicket.compare(winningLottoTicket, bonusNumber)).isEqualTo(Rank.FIFTH);
    }
}