package service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import model.Lotto;
import model.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    private LottoFactory lottoFactory;

    @BeforeEach
    void beforeEach() {
        Integer purchase = 10000;
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

    @Test
    @DisplayName("수익률은 당첨합계 / 원금 이어야 한다.")
    void validBenefit() {
        EnumMap<Prize, Integer> prizeMap = Prize.initializeMap();
        prizeMap.put(Prize.MATCH_THREE, 3);

        double benefit = lottoFactory.getBenefit(prizeMap);
        double expected = 1.5;
        assertThat(benefit).isEqualTo(expected);
    }
}
