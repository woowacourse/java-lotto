package lotto.domain;

import static lotto.domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    private Lottos lottos;

    @BeforeEach
    void setUp() {
        lottos = new Lottos(Stream.of(createLottoNumbers(1, 2, 3, 4, 5, 6),
                        createLottoNumbers(2, 3, 4, 5, 6, 7))
                .map(Lotto::new)
                .collect(Collectors.toList()));
    }

    @DisplayName("수익률을 계산한다")
    @Test
    void calculateYield() {
        final Map<Rank, Integer> rankResults = Rank.initResultMap();
        rankResults.put(Rank.FIFTH, 1);
        rankResults.put(Rank.NOT_THING, 13);

        final LottoResult lottoResult = new LottoResult(rankResults);

        assertThat(lottoResult.calculateYield()).isEqualTo(5000 / 14000.0);
    }

    @Test
    void createLottoResult() {
        // given
        final WinLotto winLotto = new WinLotto(new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7));

        final Map<Rank, Integer> resultMap = Rank.initResultMap();
        resultMap.put(Rank.FIRST, 1);
        resultMap.put(Rank.SECOND, 1);
        final LottoResult expected = new LottoResult(resultMap);

        // when
        final LottoResult result = LottoResult.createLottoResult(lottos, winLotto);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
