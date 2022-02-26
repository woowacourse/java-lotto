package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankPriceTest {

    @DisplayName("일치하는 당첨 번호 갯수에 해당하는 상금 반환을 확인한다.")
    @Test
    void find_winPrice_by_correctNumber() {
        final RankPrice firstPrice = RankPrice.findByCount(6, false);

        assertThat(firstPrice).isEqualTo(RankPrice.FIRST);
    }
}
