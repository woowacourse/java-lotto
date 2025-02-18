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

    @Test
    void 당첨_로또_번호_개수가_2개_이하인_경우_수익() {
        int correctNumberCount = 2;
        boolean isBonus = false;
        int expectProfit = 0;

        LottoResult.addCount(correctNumberCount, isBonus);

        for (LottoResult result : LottoResult.values()) {
            Assertions.assertThat(result.getCount()).isEqualTo(expectProfit);
        }
    }

    @Test
    void 최종_수익률_테스트() {
        int price = 14000;
        int correctNumberCount = 3;
        boolean isBonus = false;
        double result = LottoResult.lottoRateOfReturn(price);
        double expectRateOfReturn = 0.35;

        LottoResult.addCount(correctNumberCount, isBonus);

        Assertions.assertThat(result).isEqualTo(expectRateOfReturn);
    }

}