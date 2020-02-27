package lotto;

import domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
    @Test
    @DisplayName("당첨번호 매칭 개수와 보너스 번 매칭여부로 로또 등수 확인")
    void findLottoRankWithMatchingCount() {
        assertThat(LottoRank.findRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.findRank(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.findRank(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.findRank(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.findRank(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.findRank(2, false)).isEqualTo(null);
    }
}
