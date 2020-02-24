package lotto;

import domain.LottoRank;
import domain.LottoResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 로또_결과의_초기값_확인() {
        LottoResult lottoResult = new LottoResult();
        assertThat(lottoResult.getSize()).isEqualTo(5);

        for (LottoRank rank : LottoRank.values()) {
            assertThat(lottoResult.getRankCount(rank)).isEqualTo(0);
        }
    }
}
