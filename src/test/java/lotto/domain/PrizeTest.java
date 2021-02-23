package lotto.domain;

import lotto.domain.result.Prize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PrizeTest {
    @Test
    @DisplayName("second prize 찾기 확인")
    void findPrize2() {
        assertThat(Prize.findPrize(5, true))
                .isEqualTo(Prize.SECOND_PRIZE);
    }

    @Test
    @DisplayName("third prize 찾기 확인")
    void findPrize3() {
        assertThat(Prize.findPrize(5, false))
                .isEqualTo(Prize.THIRD_PRIZE);
    }

    @Test
    @DisplayName("상금 가져오기 확인")
    void getPrizeMoney() {
        assertThat(Prize.SECOND_PRIZE
                .getPrizeMoney().getValue())
                .isEqualTo(new Money(30000000).getValue());
    }

    @Test
    @DisplayName("일치하는 갯수 가져오기 확인")
    void getMatchCount() {
        assertThat(Prize.SECOND_PRIZE
                .getMatchCount())
                .isEqualTo(5);
    }
}
