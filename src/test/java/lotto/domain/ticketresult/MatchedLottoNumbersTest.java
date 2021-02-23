package lotto.domain.ticketresult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import lotto.type.LottoMatchType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchedLottoNumbersTest {
    private WinningTicketAndBonusNumber winningTicketAndBonusNumber;

    @BeforeEach
    void setUp() {
        LottoTicket winningLottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.get(1),
                LottoNumbers.get(2),
                LottoNumbers.get(3),
                LottoNumbers.get(4),
                LottoNumbers.get(5),
                LottoNumbers.get(6)
            )
        );
        LottoNumber bonusNumber = LottoNumbers.get(7);
        winningTicketAndBonusNumber
            = new WinningTicketAndBonusNumber(winningLottoTicket, bonusNumber);
    }

    @DisplayName("일반 번호와 보너스 번호의 일치 결과 테스트 - 일반 번호 2개, 보너스 번호 0개")
    @Test
    void Should_Return_ExpectedMatchResult_When_Regular2Bonus0() {
        LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.get(1),
                LottoNumbers.get(2),
                LottoNumbers.get(11),
                LottoNumbers.get(12),
                LottoNumbers.get(13),
                LottoNumbers.get(14)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);

        assertThatThrownBy(matchedLottoNumbers::getMatchType)
            .isInstanceOf(IllegalArgumentException.class);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isFalse();
        assertThat(matchedLottoNumbers.size()).isEqualTo(2);
    }

    @DisplayName("일반 번호와 보너스 번호의 일치 결과 테스트 - 일반 번호 0개, 보너스 번호 1개")
    @Test
    void Should_Return_ExpectedMatchResult_When_Regular0Bonus1() {
        LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.get(11),
                LottoNumbers.get(7),
                LottoNumbers.get(13),
                LottoNumbers.get(14),
                LottoNumbers.get(15),
                LottoNumbers.get(16)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);

        assertThatThrownBy(matchedLottoNumbers::getMatchType)
            .isInstanceOf(IllegalArgumentException.class);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
        assertThat(matchedLottoNumbers.size()).isEqualTo(1);
    }

    @DisplayName("일반 번호와 보너스 번호의 일치 결과 테스트 - 일반 번호 3개, 보너스 번호 1개")
    @Test
    void Should_Return_ExpectedMatchResult_When_Regular3Bonus1() {
        LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumbers.get(1),
                LottoNumbers.get(2),
                LottoNumbers.get(3),
                LottoNumbers.get(7),
                LottoNumbers.get(13),
                LottoNumbers.get(14)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);

        assertThat(matchedLottoNumbers.getMatchType()).isEqualTo(LottoMatchType.THREE_MATCH);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
        assertThat(matchedLottoNumbers.size()).isEqualTo(4);
    }
}