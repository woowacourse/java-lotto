package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @DisplayName("사용자 로또 등수와 구입 금액을 통해 수익률을 계산한다")
    @Test
    void calculateProfitRateTest() {
        EnumMap<Rank, Integer> ranks = new EnumMap<>(Map.of(
                Rank.FIRST, 0,
                Rank.SECOND, 0,
                Rank.THIRD, 0,
                Rank.FOURTH, 1,
                Rank.FIFTH, 1,
                Rank.FAIL, 5
        ));

        LottoResult lottoResult = new LottoResult(ranks);

        UserLotto userLotto = new UserLotto("7000");
        assertThat(lottoResult.getProfitRate(userLotto))
                .isCloseTo(7.857, within(0.001));
    }

}
