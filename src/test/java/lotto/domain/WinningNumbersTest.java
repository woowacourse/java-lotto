package lotto.domain;

import lotto.exception.WinningNumbersException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumbersTest {
    @DisplayName("당첨번호와 보너스번호가 중복될 경우 예외 발생")
    @Test
    void duplicatedWinningNumbers() {
        Assertions.assertThatThrownBy(() -> {
            LottoTicket lottoTicket = createLottoTicket("1,2,3,4,5,6");
            LottoNumber bonusNumber = new LottoNumber(6);
            new WinningNumbers(lottoTicket, bonusNumber);
        }).isInstanceOf(WinningNumbersException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("로또티켓들을 당첨번호와 비교해서 해당 순위들을 반환")
    @Test
    void returnRanks() {
        LottoTicket lottoTicket = createLottoTicket("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(lottoTicket, bonusNumber);

        List<String> inputsForManualLottoTickets = Arrays.asList("1,2,3,10,11,12",
                "1,2,3,4,5,12",
                "1,2,3,4,5,6");
        LottoTickets lottoTickets = LottoTickets.ofManualLottoTickets(3, inputsForManualLottoTickets);

        List<Rank> givenRanks = winningNumbers.checkOutLottos(lottoTickets);
        List<Rank> expectedRanks = Arrays.asList(Rank.FIRST, Rank.THIRD, Rank.FIFTH);

        for (Rank givenRank : givenRanks) {
            assertThat(expectedRanks).contains(givenRank);
        }
    }

    private static LottoTicket createLottoTicket(String numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }
}