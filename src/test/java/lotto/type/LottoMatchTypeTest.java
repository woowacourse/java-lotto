package lotto.type;

import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;
import static lotto.type.LottoMatchType.FOUR_MATCH;
import static lotto.type.LottoMatchType.SIX_MATCH;
import static lotto.type.LottoMatchType.THREE_MATCH;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMatchTypeTest {
    @DisplayName("THREE_MATCH get 함수 테스트")
    @Test
    void Should_Return_ExpectedResults_When_GetFromTHREE_MATCH() {
        assertThat(THREE_MATCH.getCountExceptBonusNumber()).isEqualTo(3);
        assertThat(THREE_MATCH.getPrizeMoney()).isEqualTo(5_000);
        assertThat(THREE_MATCH.getMatchCountMessage()).isEqualTo("3개 일치 (5000원) - %d개");
    }

    @DisplayName("FOUR_MATCH get 함수 테스트")
    @Test
    void Should_Return_ExpectedResults_When_GetFromFour_MATCH() {
        assertThat(FOUR_MATCH.getCountExceptBonusNumber()).isEqualTo(4);
        assertThat(FOUR_MATCH.getPrizeMoney()).isEqualTo(50_000);
        assertThat(FOUR_MATCH.getMatchCountMessage()).isEqualTo("4개 일치 (50000원) - %d개");
    }

    @DisplayName("FIVE_MATCH get 함수 테스트")
    @Test
    void Should_Return_ExpectedResults_When_GetFromFive_MATCH() {
        assertThat(FIVE_MATCH.getCountExceptBonusNumber()).isEqualTo(5);
        assertThat(FIVE_MATCH.getPrizeMoney()).isEqualTo(1_500_000);
        assertThat(FIVE_MATCH.getMatchCountMessage()).isEqualTo("5개 일치 (1500000원) - %d개");
    }

    @DisplayName("FIVE_AND_BONUS_MATCH get 함수 테스트")
    @Test
    void Should_Return_ExpectedResults_When_GetFromFIVE_AND_BONUS_MATCH() {
        assertThat(FIVE_AND_BONUS_MATCH.getCountExceptBonusNumber()).isEqualTo(5);
        assertThat(FIVE_AND_BONUS_MATCH.getPrizeMoney()).isEqualTo(30_000_000);
        assertThat(FIVE_AND_BONUS_MATCH.getMatchCountMessage())
            .isEqualTo("5개 일치, 보너스 볼 일치(30000000원) - %d개");
    }

    @DisplayName("SIX_MATCH get 함수 테스트")
    @Test
    void Should_Return_ExpectedResults_When_GetFromSIX_MATCH() {
        assertThat(SIX_MATCH.getCountExceptBonusNumber()).isEqualTo(6);
        assertThat(SIX_MATCH.getPrizeMoney()).isEqualTo(2_000_000_000);
        assertThat(SIX_MATCH.getMatchCountMessage()).isEqualTo("6개 일치 (2000000000원) - %d개");
    }
}
