package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BuyerTest {

    @ParameterizedTest
    @DisplayName("로또 당첨 개수 세기 테스트")
    @CsvSource({
        "1, 2, 3, 4, 5, 6, FIRST_PLACE",
        "1, 2, 3, 4, 5, 7, SECOND_PLACE",
        "1, 2, 3, 4, 5, 10, THIRD_PLACE",
        "1, 2, 3, 4, 9, 10, FOURTH_PLACE",
        "1, 2, 3, 8, 9, 10, FIFTH_PLACE",
        "10, 11, 12, 13, 14, 15, BOOM"
    })
    void success_1(int n1, int n2, int n3, int n4, int n5, int n6, LottoRank expectedRank) {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Buyer buyer = new Buyer(List.of(createLotto(n1, n2, n3, n4, n5, n6)));

        LottoStatistics lottoStatistics = buyer.countMatchedRanks(winningLotto);
        Map<LottoRank, Integer> result = lottoStatistics.getLottoCounter();

        Assertions.assertThat(result.get(expectedRank)).isEqualTo(1);
    }

    private static Lotto createLotto(Integer... numbers) {
        return new Lotto(Arrays.stream(numbers).toList());
    }
}
