package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCompanyTest {

    @Test
    void makeWinningLotto() {
        //given
        int[] winningNumbers = {1, 2, 3, 4, 5, 6};
        LottoTicket expectedTicket = new LottoTicket(new HashSet<>(Arrays.asList(LottoBall.of(1),
                LottoBall.of(2),
                LottoBall.of(3),
                LottoBall.of(4),
                LottoBall.of(5),
                LottoBall.of(6))));
        //when
        LottoTicket winningTicket = LottoCompany.makeWinningLotto(winningNumbers);
        //then
        assertThat(winningTicket).isEqualTo(expectedTicket);
    }
}
