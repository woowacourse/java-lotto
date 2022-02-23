package lotto.domain;

import static lotto.domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    void constructor() {
        final Lottos lottos = new Lottos(Stream.of(createLottoNumbers(1, 2, 3, 4, 5, 6),
                        createLottoNumbers(2, 3, 4, 5, 6, 7))
                .map(Lotto::new)
                .collect(Collectors.toList()));
        assertThat(lottos).isInstanceOf(Lottos.class);
    }

    @Test
    void createLottoResult() {
        // given
        final Lottos lottos = new Lottos(Stream.of(createLottoNumbers(1, 2, 3, 4, 5, 6),
                        createLottoNumbers(2, 3, 4, 5, 6, 7))
                .map(Lotto::new)
                .collect(Collectors.toList()));
        final WinLotto winLotto = new WinLotto(new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

        final Map<Rank, Integer> resultMap = new HashMap<>();
        resultMap.put(Rank.FIRST, 1);
        resultMap.put(Rank.SECOND, 1);
        final LottoResult expected = new LottoResult(resultMap);

        // when
        final LottoResult result = lottos.createResult(winLotto);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
