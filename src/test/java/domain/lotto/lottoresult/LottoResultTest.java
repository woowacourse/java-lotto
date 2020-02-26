package domain.lotto.lottoresult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LottoResultTest {
    @Test
    @DisplayName("생성 테스트")
    void test1() {
        Map<LottoRank, ResultCount> result = new HashMap<>();
        result.put(LottoRank.FIRST, new ResultCount(1));
        result.put(LottoRank.SECOND, new ResultCount(1));
        result.put(LottoRank.THIRD, new ResultCount(1));
        result.put(LottoRank.FOURTH, new ResultCount(1));
        result.put(LottoRank.FIFTH, new ResultCount(1));
        result.put(LottoRank.NOTHING, new ResultCount(1));
        Assertions.assertThatCode(() -> new LottoResult(result)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("null입력 방어 테스트")
    void test2() {
        Assertions.assertThatThrownBy(() -> new LottoResult(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    @DisplayName("총 당첨금액 확인")
    void test3() {
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
    @DisplayName("Rank에 해당하는 ResultCount을 제공")
    void test4() {
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
