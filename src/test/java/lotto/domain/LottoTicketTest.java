package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {
    @Test
    void 당첨번호가_있는지_확인() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getNumber(1), LottoNumber.getNumber(2),
                LottoNumber.getNumber(3), LottoNumber.getNumber(4),
                LottoNumber.getNumber(5), LottoNumber.getNumber(6));
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        WinningLotto winningLotto = WinningLotto.of("3,7,12,5,9,1", 40);

        assertThat(lottoTicket.getMatchingCount(winningLotto)).isEqualTo(3);
    }
}
