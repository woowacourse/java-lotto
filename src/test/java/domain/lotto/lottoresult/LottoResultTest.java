package domain.lotto.lottoresult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LottoResultTest {
    @Test
    void 생성_테스트() {
        Map<LottoRank, ResultCount> result = new HashMap<>();
        result.put(LottoRank.FIRST, new ResultCount(1));
        result.put(LottoRank.SECOND, new ResultCount(1));
        result.put(LottoRank.THIRD, new ResultCount(1));
        result.put(LottoRank.FOURTH, new ResultCount(1));
        result.put(LottoRank.FIFTH, new ResultCount(1));
        result.put(LottoRank.NOTHING, new ResultCount(1));

        LottoResult lottoResult = new LottoResult(result);

        Assertions.assertThat(lottoResult)
                .hasFieldOrProperty("result")
                .isNotNull();
    }

    @Test
    void null입력_방어_테스트() {
        Assertions.assertThatThrownBy(() -> new LottoResult(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    void 총_당첨금액_확인() {
        Map<LottoRank, ResultCount> result = new HashMap<>();
        result.put(LottoRank.FIRST, new ResultCount(1));
        result.put(LottoRank.SECOND, new ResultCount(1));
        result.put(LottoRank.THIRD, new ResultCount(1));
        result.put(LottoRank.FOURTH, new ResultCount(1));
        result.put(LottoRank.FIFTH, new ResultCount(1));
        result.put(LottoRank.NOTHING, new ResultCount(1));

        LottoResult lottoResult = new LottoResult(result);

        Assertions.assertThat(lottoResult.calculateEarning()).isEqualTo(2_031_555_000);
    }

    @Test
    void Rank에_해당하는_ResultCount을_제공() {
        Map<LottoRank, ResultCount> result = new HashMap<>();
        result.put(LottoRank.FIRST, new ResultCount(1));
        result.put(LottoRank.SECOND, new ResultCount(1));
        result.put(LottoRank.THIRD, new ResultCount(1));
        result.put(LottoRank.FOURTH, new ResultCount(1));
        result.put(LottoRank.NOTHING, new ResultCount(1));

        LottoResult lottoResult = new LottoResult(result);
        Assertions.assertThat(lottoResult.countRank(LottoRank.FIRST)).isEqualTo(new ResultCount(1));
        Assertions.assertThat(lottoResult.countRank(LottoRank.FIFTH)).isEqualTo(new ResultCount(0));
    }
}
