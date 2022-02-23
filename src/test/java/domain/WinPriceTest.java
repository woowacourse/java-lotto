package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinPriceTest {

    @DisplayName("일치하는 당첨 번호 갯수에 해당하는 상금 반환을 확인한다.")
    @Test
    void find_winPrice_by_winCount() {
        final WinPrice firstPrice = WinPrice.findByCount(6);

        assertThat(firstPrice).isEqualTo(WinPrice.FIRST);
    }
}
