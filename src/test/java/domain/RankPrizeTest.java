package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankPrizeTest {

    @DisplayName("일치하는 당첨 번호 갯수에 해당하는 상금 반환을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"6,2000000000,false", "5, 30000000, true", "5, 1500000, false",
            "4, 50000, false", "3, 5000, false"}, delimiter = ',')
    void find_winPrize_by_correctNumber(int correctNumber, int prize, boolean secondRank) {
        final RankPrize rank = RankPrize.findByCount(correctNumber, secondRank);

        assertThat(rank.getPrize()).isEqualTo(prize);
    }

    @DisplayName("일치하는 당첨 번호 갯수에 해당하는 상금이 없을 경우 예외를 발생시킨다.")
    @Test
    void find_winPrize_invalid() {
        assertThatThrownBy(() -> RankPrize.findByCount(2, false))
                .isInstanceOf(RuntimeException.class).hasMessage("일치하는 값이 없습니다.");
    }
}
