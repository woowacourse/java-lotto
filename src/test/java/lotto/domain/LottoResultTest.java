package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @DisplayName("null로 객체생성하면 예외 발생")
    @Test
    void nullRankResultException() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new LottoResult(null))
                .withMessage("[ERROR] LottoResult는 null로 생성할 수 없습니다.");
    }

    @DisplayName("수익률을 계산한다")
    @Test
    void calculateYield() {
        final Map<Rank, Integer> rankResults = Rank.createInitResultMap();
        rankResults.put(Rank.FIFTH, 1);
        rankResults.put(Rank.NOT_THING, 13);

        final LottoResult lottoResult = new LottoResult(rankResults);

        assertThat(lottoResult.calculateYield()).isEqualTo(5000 / 14000.0);
    }
}
