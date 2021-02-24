package lotto.domain.ticketresult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.domain.LottoNumber;
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
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)
            )
        );
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        winningTicketAndBonusNumber
            = new WinningTicketAndBonusNumber(winningLottoTicket, bonusNumber);
    }

    @DisplayName("일반 번호와 보너스 번호의 일치 결과 테스트 - 일반 번호 2개, 보너스 번호 0개")
    @Test
    void Should_Return_ExpectedMatchResult_When_Regular2Bonus0() {
        LottoTicket lottoTicket = new LottoTicket(
            Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(11),
                LottoNumber.valueOf(12),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(14)
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
                LottoNumber.valueOf(11),
                LottoNumber.valueOf(7),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(14),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(16)
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
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(7),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(14)
            )
        );

        MatchedLottoNumbers matchedLottoNumbers
            = winningTicketAndBonusNumber.getMatchedLottoNumbers(lottoTicket);

        assertThat(matchedLottoNumbers.getMatchType()).isEqualTo(LottoMatchType.THREE_MATCH);
        assertThat(matchedLottoNumbers.isContainsBonusNumber()).isTrue();
        assertThat(matchedLottoNumbers.size()).isEqualTo(4);
    }
}