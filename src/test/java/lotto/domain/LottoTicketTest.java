package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {
    private final WinningLotto winningLotto = WinningLotto.of("1,3,5,7,9,12", 40);
    private LottoTicket lottoTicket;

    @Test
    void create() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getInstance(1), LottoNumber.getInstance(2), LottoNumber.getInstance(3),
                LottoNumber.getInstance(4), LottoNumber.getInstance(5), LottoNumber.getInstance(6));

        assertThat(new LottoTicket(lottoNumbers)).isEqualTo(new LottoTicket(lottoNumbers));
    }

    @Test
    void 당첨번호와_일치하는_번호수_확인() {
        lottoTicket = new LottoTicket(Arrays.asList(
                LottoNumber.getInstance(1), LottoNumber.getInstance(2), LottoNumber.getInstance(3),
                LottoNumber.getInstance(4), LottoNumber.getInstance(5), LottoNumber.getInstance(6)));

        //assertThat(lottoTicket.getMatchingCount(winningLotto)).isEqualTo(3);
    }

    @Test
    void 보너스볼과_일치하는경우_확인() {
        lottoTicket = new LottoTicket(Arrays.asList(
                LottoNumber.getInstance(1), LottoNumber.getInstance(2), LottoNumber.getInstance(3),
                LottoNumber.getInstance(4), LottoNumber.getInstance(5), LottoNumber.getInstance(40)));

        assertThat(lottoTicket.matchesBonusBall(winningLotto)).isTrue();
    }
}
