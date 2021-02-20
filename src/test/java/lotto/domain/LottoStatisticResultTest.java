package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoStatisticResultTest {

    private static final WinningLotto WINNING_LOTTO
        = WinningLotto.of(Arrays.asList(1, 2, 3, 4, 5, 6), LottoNumber.from("7"));

    @Test
    void match_ReturnProperMatchingCount() {
        // given
        List<Lotto> lottoGroup = new ArrayList<>();
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(7, 8, 9, 10, 11, 12)));

        Lottos lottos = Lottos.from(lottoGroup);

        LottoStatisticResult result = Rank.match(lottos, WINNING_LOTTO);

        // when
        Long count = result.getOrNoCount(Rank.FIRST);

        // then
        assertThat(count).isEqualTo(1L);
    }

    @Test
    void calculateIncomeRate() {
        // given
        List<Lotto> lottoGroup = new ArrayList<>();
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 43, 44, 45))); // 5등 - 5000원
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 44, 45))); // 4등 - 50000원
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(40, 41, 42, 43, 44, 45))); // 당첨 X - 0원

        Lottos lottos = Lottos.from(lottoGroup);
        LottoStatisticResult result = Rank.match(lottos, WINNING_LOTTO);

        // when
        double incomeRate = result.calculateIncomeRate();

        // then
        assertThat(incomeRate).isEqualTo((double) 55000 / 3000);
    }
}
