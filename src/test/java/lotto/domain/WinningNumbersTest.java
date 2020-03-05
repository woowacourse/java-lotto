package lotto.domain;

import lotto.exception.WinningNumbersException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumbersTest {
    @DisplayName("당첨번호와 보너스번호가 중복될 경우 예외 발생")
    @Test
    void duplicatedWinningNumbers() {
        Assertions.assertThatThrownBy(() -> {
            LottoTicket lottoTicket = LottoTicket.createManualTicket("1,2,3,4,5,6");
            LottoNumber bonusNumber = LottoNumber.valueOf(6);
            new WinningNumbers(lottoTicket, bonusNumber);
        }).isInstanceOf(WinningNumbersException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("로또티켓들을 당첨번호와 비교해서 해당 순위들을 반환")
    @Test
    void returnRanks() {
        Money purchaseMoney = Money.createPurchaseMoney(14000);
        TicketCounts ticketCounts = TicketCounts.from(purchaseMoney, 3);
        List<String> manualLottoNumbers = Arrays.asList("1,2,3,10,11,12",
                "1,2,3,4,5,12",
                "1,2,3,4,5,6");
        LottoTickets lottoTickets = LottoTickets.from(ticketCounts, manualLottoNumbers);

        LottoTicket winningLottoTicket = LottoTicket.createManualTicket("1,2,3,4,5,6");
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoTicket, bonusNumber);

        Ranks givenRanks = winningNumbers.checkOutLottos(lottoTickets);

        assertThat(givenRanks.contains(Rank.FIRST)).isTrue();
        assertThat(givenRanks.contains(Rank.THIRD)).isTrue();
        assertThat(givenRanks.contains(Rank.FIFTH)).isTrue();
    }
}