package lotto.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTypeCountTest {

    @Test
    @DisplayName("key에 맞게 카운트를 올려주는지 테스트")
    void update() {
        LottoResult.updateCount(RankType.FOUR);
        assertThat(LottoResult.lottoResultCount.get(RankType.FOUR)).isEqualTo(1);
    }
}
