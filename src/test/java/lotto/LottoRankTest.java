package lotto;

import domain.LottoRank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
    @Test
    void 로또_등수_확인(){
        assertThat(LottoRank.findRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.findRank(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.findRank(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.findRank(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.findRank(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.findRank(2, false)).isEqualTo(null);
    }
}
