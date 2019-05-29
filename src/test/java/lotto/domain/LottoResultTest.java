package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 결과가_올바르게_추가되는지_확인() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.add(Rank.FIRST);
        lottoResult.add(Rank.FIRST);
        lottoResult.add(Rank.THIRD);
        lottoResult.add(Rank.FOURTH);
        lottoResult.add(Rank.FIFTH);
        lottoResult.add(Rank.MISS);

        Map<Rank, Integer> result = new HashMap<>();
        result.put(Rank.FIRST, 2);
        result.put(Rank.THIRD, 1);
        result.put(Rank.FOURTH, 1);
        result.put(Rank.FIFTH, 1);
        result.put(Rank.MISS, 1);

        assertThat(lottoResult).isEqualTo(new LottoResult(result));
    }
}
