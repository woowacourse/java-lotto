package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("해당 당첨 티켓이 몇개 있는 지 확인하는 기능")
    void getCountByPrizeType() {
        List<PrizeType> prizeTypes = Arrays.asList(PrizeType.FIRST_PRIZE, PrizeType.FIRST_PRIZE, PrizeType.FIRST_PRIZE);
        LottoResult lottoResult = new LottoResult(prizeTypes);
        assertThat(lottoResult.getCountByPrizeType(PrizeType.FIRST_PRIZE)).isEqualTo(3);
    }
}