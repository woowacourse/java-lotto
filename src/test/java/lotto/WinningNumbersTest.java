package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumbersTest {
    @Test
    void 당첨번호와_보너스번호가_중복될_경우_예외_발생() {
        Assertions.assertThatThrownBy(() -> {
            LottoTicket lottoTicket = createLottoTicket("1,2,3,4,5,6");
            LottoNumber bonusNumber = new LottoNumber(6);
            new WinningNumbers(lottoTicket, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    void compareLottos() {
        LottoTicket lottoTicket = createLottoTicket("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(lottoTicket, bonusNumber);

        LottoTicket lottoTicketForFirstRank = createLottoTicket("1,2,3,10,11,12");
        LottoTicket lottoTicketForThirdRank = createLottoTicket("1,2,3,4,5,12");
        LottoTicket lottoTicketForFifthRank = createLottoTicket("1,2,3,4,5,6");
        List<LottoTicket> lottoTickets = Arrays.asList(lottoTicketForFirstRank, lottoTicketForThirdRank, lottoTicketForFifthRank);

        List<Rank> givenRanks = winningNumbers.compareLottos(lottoTickets);
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