package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {
    private LottoTicket firstPrizeTicket;
    private LottoTicket secondPrizeTicket;
    private LottoTickets lottoTickets;
    private List<LottoNumber> winningNumbers;
    private LottoNumber bonusBall;
    private List<Prize> result;

    @BeforeEach
    void init() {
        firstPrizeTicket = new LottoTicket(Arrays.asList(
                LottoNumber.of("1"),
                LottoNumber.of("2"),
                LottoNumber.of("3"),
                LottoNumber.of("4"),
                LottoNumber.of("5"),
                LottoNumber.of("7")));

        secondPrizeTicket = new LottoTicket(Arrays.asList(
                LottoNumber.of("1"),
                LottoNumber.of("2"),
                LottoNumber.of("3"),
                LottoNumber.of("4"),
                LottoNumber.of("5"),
                LottoNumber.of("8")));

        lottoTickets = new LottoTickets(Arrays.asList(firstPrizeTicket, secondPrizeTicket));

        winningNumbers = Arrays.asList(
                LottoNumber.of("1"),
                LottoNumber.of("2"),
                LottoNumber.of("3"),
                LottoNumber.of("4"),
                LottoNumber.of("5"),
                LottoNumber.of("7"));

        bonusBall = LottoNumber.of("8");
        result = lottoTickets.checkWinningTickets(new WinningLotto(new LottoTicket(winningNumbers), bonusBall));
    }

    @Test
    @DisplayName("당첨 티켓 분류")
    void checkWinningTicket() {
        assertThat(result.get(0)).isEqualTo(Prize.FIRST_PRIZE);
        assertThat(result.get(1)).isEqualTo(Prize.SECOND_PRIZE);
    }
}