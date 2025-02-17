package domain;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuyerTest {

    @Test
    @DisplayName("로또 당첨 개수 세기 테스트")
    void success_1() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // 로또 6개 생성
        Buyer buyer = new Buyer(
            List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 10)), // 3등
                new Lotto(List.of(1, 2, 3, 4, 9, 10)), // 4등
                new Lotto(List.of(1, 2, 3, 8, 9, 10)), // 5등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝
            ));
        Map<LottoRank, Integer> result = buyer.countMatchedRanks(winningLotto);

        Assertions.assertThat(result.get(LottoRank.FIRST_PLACE)).isEqualTo(1);
        Assertions.assertThat(result.get(LottoRank.SECOND_PLACE)).isEqualTo(1);
        Assertions.assertThat(result.get(LottoRank.THIRD_PLACE)).isEqualTo(1);
        Assertions.assertThat(result.get(LottoRank.FOURTH_PLACE)).isEqualTo(1);
        Assertions.assertThat(result.get(LottoRank.FIFTH_PLACE)).isEqualTo(1);
        Assertions.assertThat(result.get(LottoRank.BOOM)).isEqualTo(1);
    }
}
