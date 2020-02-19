package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCompanyTest {

    @Test
    void buyTicket() {
        //given
        int money = 1000;

        //when
        List<LottoTicket> lottoTickets = LottoCompany.buyTicket(money);

        //then
        LottoTicket lottoTicket = lottoTickets.get(0);
        assertThat(lottoTicket).isEqualTo(new LottoTicket(new HashSet<>(Arrays.asList(
                LottoBall.of(1),
                LottoBall.of(2),
                LottoBall.of(3),
                LottoBall.of(4),
                LottoBall.of(5),
                LottoBall.of(6)))));
    }
}