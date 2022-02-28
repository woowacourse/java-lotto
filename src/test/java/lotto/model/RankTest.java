package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("맞은 볼 개수와 보너스 볼 포함 여부에 맞는 등수를 반환한다.")
    void matchTest() {
        Lottos lottos = new Lottos(Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
            new Lotto(Arrays.asList(1, 2, 3, 4, 5, 44))));
        Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 45));
        LottoNumber bonusNumber = new LottoNumber(6);
        List<Lotto> lottoList = lottos.getLottos();

        List<Rank> actual = lottoList.stream()
            .map(lotto -> Rank.match(lotto, winningNumbers, bonusNumber))
            .collect(Collectors.toList());

        List<Rank> expected = Arrays.asList(Rank.SECOND, Rank.THIRD);
        assertThat(actual).isEqualTo(expected);
    }
}
