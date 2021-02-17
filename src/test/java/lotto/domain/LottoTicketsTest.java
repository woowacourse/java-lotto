package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {
    private LottoTicket firstPrizeTicket;
    private LottoTicket secondPrizeTicket;
    private LottoTickets lottoTickets ;
    private List<LottoNumber> winningNumbers;
    private LottoNumber bonusBall;
    private Map<PrizeType, Integer> result;

    @BeforeEach
    void init() {
        firstPrizeTicket = new LottoTicket(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("7")));

        secondPrizeTicket = new LottoTicket(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("8")));

        lottoTickets = new LottoTickets(Arrays.asList(firstPrizeTicket, secondPrizeTicket));

        winningNumbers = Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("7"));

        bonusBall = new LottoNumber("8");
        result = lottoTickets.checkWinningTickets(winningNumbers, bonusBall);
    }

    @Test
    @DisplayName("당첨 티켓 분류")
    void checkWinningTicket() {
        assertThat(result.get(PrizeType.FIRST_PRIZE)).isEqualTo(1);
        assertThat(result.get(PrizeType.SECOND_PRIZE)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void calculateProfitRate() {
        Money money = new Money(2000);
        double profitRate = lottoTickets.calculateProfitRate(money, result);
        assertThat(profitRate).isEqualTo(1015000.0);
    }
}