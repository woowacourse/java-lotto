package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumbersTest {
    @Test
    void 당첨번호와_보너스번호가_중복될_경우_예외_발생() {
        Assertions.assertThatThrownBy(() -> {
            List<LottoNumber> sixNumbers = Arrays.asList(new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6));
            LottoTicket lottoTicket = new LottoTicket(sixNumbers);
            LottoNumber bonusNumber = new LottoNumber(6);
            new WinningNumbers(lottoTicket, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    void compareLottos() {
        List<LottoNumber> sixNumbers = Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));
        LottoTicket lottoTicket = new LottoTicket(sixNumbers);
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(lottoTicket, bonusNumber);

        LottoTicket lottoTicketForFirstRank = new LottoTicket(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(10),
                new LottoNumber(11),
                new LottoNumber(12)));
        LottoTicket lottoTicketForThirdRank = new LottoTicket(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(12)));
        LottoTicket lottoTicketForFifthRank = new LottoTicket(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));
        List<LottoTicket> lottoTickets = Arrays.asList(lottoTicketForFirstRank, lottoTicketForThirdRank, lottoTicketForFifthRank);

        List<Rank> givenRanks = winningNumbers.compareLottos(lottoTickets);
        List<Rank> expectedRanks = Arrays.asList(Rank.FIRST, Rank.THIRD, Rank.FIFTH);

        for (Rank givenRank : givenRanks) {
            assertThat(expectedRanks).contains(givenRank);
        }
    }
}