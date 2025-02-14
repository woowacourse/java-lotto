package service;

import static org.assertj.core.api.Assertions.assertThat;

import model.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    private LottoFactory lottoFactory;

    @BeforeEach
    void beforeEach() {
        int purchase = 10000;
        lottoFactory = LottoFactory.of(purchase);
    }

    @Test
    @DisplayName("구매 티켓 갯수는 구매 금액에 나누기 1000이여야 한다.")
    void validTicketNumber() {
        int expected = 10;
        assertThat(lottoFactory.getTicketNumber()).isEqualTo(expected);
    }

    @Test
    @DisplayName("발행된 티켓 내 숫자들은 모두 1 부터 45 사이여야 한다.")
    void validTicketRange() {
        for (Lotto issuedTicket : lottoFactory.getIssuedTickets()) {
            issuedTicket.getNumbers()
                    .forEach(number -> assertThat(number).isBetween(1, 45));
        }
    }

    @Test
    @DisplayName("발행된 티켓 내 숫자 갯수는 6개여야 한다.")
    void validTicketSize() {
        for (Lotto issuedTicket : lottoFactory.getIssuedTickets()) {
            assertThat(issuedTicket.getNumbers().size()).isEqualTo(6);
        }
    }
}
