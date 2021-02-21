package lotto.type;

import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;
import static lotto.type.LottoMatchType.FOUR_MATCH;
import static lotto.type.LottoMatchType.SIX_MATCH;
import static lotto.type.LottoMatchType.THREE_MATCH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.ticketresult.MatchedLottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMatchTypeTest {
    @DisplayName("일치하는 번호가 3개일 때 THREE_MATCH 반환")
    @Test
    void Should_Return_THREE_MATCH_When_ThreeNumbersMatched() {
        MatchedLottoNumbers matchedLottoNumbersToGetPrize = new MatchedLottoNumbers(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3)
            ),
            null
        );

        assertThat(LottoMatchType.getLottoMatchType(matchedLottoNumbersToGetPrize))
            .isEqualTo(THREE_MATCH);
    }

    @DisplayName("일치하는 번호가 4개일 때 FOUR_MATCH 반환")
    @Test
    void Should_Return_FOUR_MATCH_When_FourNumbersMatched() {
        MatchedLottoNumbers matchedLottoNumbersToGetPrize = new MatchedLottoNumbers(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4)
            ),
            new LottoNumber(5)
        );

        assertThat(LottoMatchType.getLottoMatchType(matchedLottoNumbersToGetPrize))
            .isEqualTo(FOUR_MATCH);
    }

    @DisplayName("일치하는 번호가 5개일 때 FIVE_MATCH 반환")
    @Test
    void Should_Return_FIVE_MATCH_When_FiveNumbersMatched() {
        MatchedLottoNumbers matchedLottoNumbersToGetPrize = new MatchedLottoNumbers(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
            ),
            null
        );

        assertThat(LottoMatchType.getLottoMatchType(matchedLottoNumbersToGetPrize))
            .isEqualTo(FIVE_MATCH);
    }

    @DisplayName("5개의 일반 번호와 보너스 번호가 일치할 때 FIVE_AND_BONUS_MATCH 반환")
    @Test
    void Should_Return_FIVE_AND_BONUS_MATCH_When_FiveNumbersBonusNumberMatched() {
        MatchedLottoNumbers matchedLottoNumbersToGetPrize = new MatchedLottoNumbers(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
            ),
            new LottoNumber(7)
        );

        assertThat(LottoMatchType.getLottoMatchType(matchedLottoNumbersToGetPrize))
            .isEqualTo(FIVE_AND_BONUS_MATCH);
    }

    @DisplayName("일치하는 번호가 6개일 때 SIX_MATCH 반환")
    @Test
    void Should_Return_SIX_MATCH_When_SixNumbersMatched() {
        MatchedLottoNumbers matchedLottoNumbersToGetPrize = new MatchedLottoNumbers(
            Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
            ),
            null
        );

        assertThat(LottoMatchType.getLottoMatchType(matchedLottoNumbersToGetPrize))
            .isEqualTo(SIX_MATCH);
    }
}
