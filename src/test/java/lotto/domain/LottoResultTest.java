package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @DisplayName("로또 상금을 제대로 계산하는지 확인")
    @Test
    void 로또_결과_상금_계산(){
        Map<Prize,Long> lottoResultMap = new HashMap<>();
        lottoResultMap.put(Prize.FIRST, 1L); // 20억
        lottoResultMap.put(Prize.SECOND, 2L); // 6천만
        lottoResultMap.put(Prize.THIRD, 1L); // 150만
        lottoResultMap.put(Prize.FOURTH, 5L); // 25만
        lottoResultMap.put(Prize.FIFTH, 3L); // 15000

        LottoResult lottoResult = new LottoResult(lottoResultMap);

        assertThat(lottoResult.calculatePrizeMoney()).isEqualTo(2061765000L);
    }
}