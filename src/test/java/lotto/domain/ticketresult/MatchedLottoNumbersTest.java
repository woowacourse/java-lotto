package lotto.domain.ticketresult;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import lotto.domain.LottoNumber;
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
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
            )
        );
        LottoNumber bonusNumber = new LottoNumber(7);
        winningTicketAndBonusNumber
            = new WinningTicketAndBonusNumber(winningLottoTicket, bonusNumber);
    }

    @DisplayName("일치한 번호들이 보너스 번호 여부에 따라 구분되어서 결과로 나오는지 - "
        + "일반 번호 2개, 보너스 번호 0개")
    @Test
    void Should_Return_ExactClassifiedMatchResult_When_Regular2Bonus0() {
        LottoTicket purchasedLottoTicket = new LottoTicket(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(11),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14)
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
                new LottoNumber(11),
                new LottoNumber(7),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(16)
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
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(7),
                new LottoNumber(13),
                new LottoNumber(14)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(purchasedLottoTicket);

        assertThat(matchedLottoNumbers.getSizeOfNumbersNotIncludingBonusNumber()).isEqualTo(3);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
    }
}