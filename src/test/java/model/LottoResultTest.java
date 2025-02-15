package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    @DisplayName("개수 및 보너스 당첨 여부에 따라 당첨 횟수를 갱신해야 한다")
    void changeResultCount() {

        LottoResult.addCount(6, false);
        LottoResult.addCount(5, true);
        LottoResult.addCount(5, false);
        LottoResult.addCount(4, false);
        LottoResult.addCount(3, false);

        for (LottoResult result : LottoResult.values()) {
            Assertions.assertThat(result.getCount()).isEqualTo(1);
        }
    }
}
