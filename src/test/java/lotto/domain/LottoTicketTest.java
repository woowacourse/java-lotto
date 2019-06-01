package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {
    private final WinningLotto winningLotto = WinningLotto.of("1,3,5,7,9,12", 40);
    private LottoTicket lottoTicket;

    @Test
    void 당첨번호와_일치하는_번호수_확인() {
        lottoTicket = new LottoTicket(Arrays.asList(
                LottoNumber.getNumber(1), LottoNumber.getNumber(2),
                LottoNumber.getNumber(3), LottoNumber.getNumber(4),
                LottoNumber.getNumber(5), LottoNumber.getNumber(6)));

        assertThat(lottoTicket.getMatchingCount(winningLotto)).isEqualTo(3);
    }

    @Test
    void 보너스볼과_일치하는경우_확인() {
        lottoTicket = new LottoTicket(Arrays.asList(
                LottoNumber.getNumber(1), LottoNumber.getNumber(2),
                LottoNumber.getNumber(3), LottoNumber.getNumber(4),
                LottoNumber.getNumber(5), LottoNumber.getNumber(40)));

        assertThat(lottoTicket.matchesBonusBall(winningLotto)).isTrue();
    }
}
