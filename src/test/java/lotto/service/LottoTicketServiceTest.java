package lotto.service;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketServiceTest {

    @Test
    @DisplayName("생성된 로또 티켓의 클래스가 일치하는지 확인한다.")
    public void createLottoTicketClassTest() {
        assertThat(LottoTicketService.createTicket(LottoNumber.getRandomNumbers()))
                .isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("로또 티켓은 같은 번호를 가지고 있다 하더라도 다른 객체임을 테스트한다.")
    public void createLottoTicketEqualsTest() {
        LottoTicket lottoTicket1 = LottoTicketService.createTicket(
                Arrays.asList(new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)

                ));

        LottoTicket lottoTicket2 = LottoTicketService.createTicket(
                Arrays.asList(new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)

                ));
        assertThat(lottoTicket1).isNotEqualTo(lottoTicket2);
    }
}
