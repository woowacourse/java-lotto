package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void 개수_및_보너스_당첨_여부에_따라_당첨_횟수를_갱신해야_한다() {

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