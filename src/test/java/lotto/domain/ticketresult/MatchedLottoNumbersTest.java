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

    @DisplayName("일반 번호와 보너스 번호의 일치 결과 테스트 - 일반 번호 2개, 보너스 번호 0개")
    @Test
    void Should_Return_ExpectedMatchResult_When_Regular2Bonus0() {
        LottoTicket lottoTicket = new LottoTicket(
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
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);

        assertThat(matchedLottoNumbers.getSizeExceptBonusNumber()).isEqualTo(2);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isFalse();
    }

    @DisplayName("일반 번호와 보너스 번호의 일치 결과 테스트 - 일반 번호 0개, 보너스 번호 1개")
    @Test
    void Should_Return_ExpectedMatchResult_When_Regular0Bonus1() {
        LottoTicket lottoTicket = new LottoTicket(
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
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);

        assertThat(matchedLottoNumbers.getSizeExceptBonusNumber()).isEqualTo(0);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
    }

    @DisplayName("일반 번호와 보너스 번호의 일치 결과 테스트 - 일반 번호 3개, 보너스 번호 1개")
    @Test
    void Should_Return_ExpectedMatchResult_When_Regular3Bonus1() {
        LottoTicket lottoTicket = new LottoTicket(
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
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);

        assertThat(matchedLottoNumbers.getSizeExceptBonusNumber()).isEqualTo(3);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
    }
}