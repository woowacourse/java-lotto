package lotto.domain.ticketresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchedLottoNumbersTest {
    private WinningTicketAndBonusNumber winningTicketAndBonusNumber;

    @BeforeEach
    void setUp() {
        LottoTicket winningLottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.of(1),
                LottoNumbers.of(2),
                LottoNumbers.of(3),
                LottoNumbers.of(4),
                LottoNumbers.of(5),
                LottoNumbers.of(6)
            )
        );
        LottoNumber bonusNumber = LottoNumbers.of(7);
        winningTicketAndBonusNumber
            = new WinningTicketAndBonusNumber(winningLottoTicket, bonusNumber);
    }

    @DisplayName("일치한 번호들이 보너스 번호 여부에 따라 구분되어서 결과로 나오는지 - "
        + "일반 번호 2개, 보너스 번호 0개")
    @Test
    void Should_Return_ExactClassifiedMatchResult_When_Regular2Bonus0() {
        LottoTicket purchasedLottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.of(1),
                LottoNumbers.of(2),
                LottoNumbers.of(11),
                LottoNumbers.of(12),
                LottoNumbers.of(13),
                LottoNumbers.of(14)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(purchasedLottoTicket);

        assertThat(matchedLottoNumbers.getSizeOfNumbersNotIncludingBonusNumber()).isEqualTo(2);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isFalse();
    }

    @DisplayName("일치한 번호들이 보너스 번호 여부에 따라 구분되어서 결과로 나오는지 - "
        + "일반 번호 0개, 보너스 번호 1개")
    @Test
    void Should_Return_ExactClassifiedMatchResult_When_Regular0Bonus1() {
        LottoTicket purchasedLottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.of(11),
                LottoNumbers.of(7),
                LottoNumbers.of(13),
                LottoNumbers.of(14),
                LottoNumbers.of(15),
                LottoNumbers.of(16)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(purchasedLottoTicket);

        assertThat(matchedLottoNumbers.getSizeOfNumbersNotIncludingBonusNumber()).isEqualTo(0);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
    }

    @DisplayName("일치한 번호들이 보너스 번호 여부에 따라 구분되어서 결과로 나오는지 - "
        + "일반 번호 3개, 보너스 번호 1개")
    @Test
    void Should_Return_ExactClassifiedMatchResult_When_Regular3Bonus1() {
        LottoTicket purchasedLottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.of(1),
                LottoNumbers.of(2),
                LottoNumbers.of(3),
                LottoNumbers.of(7),
                LottoNumbers.of(13),
                LottoNumbers.of(14)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(purchasedLottoTicket);

        assertThat(matchedLottoNumbers.getSizeOfNumbersNotIncludingBonusNumber()).isEqualTo(3);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
    }
}