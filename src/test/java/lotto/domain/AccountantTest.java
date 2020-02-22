package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountantTest {
    private LottoTickets lottoTickets;
    private LottoResult lottoResult;
    private LottoTicket winningTicket;

    @BeforeEach
    void setUp() {
        String[][] numbers = {
            LottoTicketTest.MATCH_THREE,
            LottoTicketTest.MATCH_TWO,
            LottoTicketTest.MATCH_ONE,
            LottoTicketTest.MATCH_ONE,
            LottoTicketTest.MATCH_NONE
        };
        List<LottoTicket> sampleTickets = new ArrayList<>();
        for (String[] number : numbers) {
            sampleTickets.add(new LottoTicket(number));
        }
        lottoTickets = new LottoTickets(sampleTickets);
        winningTicket = new LottoTicket(LottoTicketTest.WINNING_NUMBER);
        lottoResult = LottoResult.create();
    }

    @Test
    @DisplayName("수익률이 정상적으로 계산되는지")
    void calculate() {
        lottoResult.match(lottoTickets, winningTicket, LottoNumber.SEVEN);
        assertThat(Accountant.calculate(Money.create("5000"), lottoResult)).isEqualTo("100.00");
    }
}
