package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또 등수 테스트")
class LottoRankTest {

    @Test
    @DisplayName("일치하는 번호 개수와 보너스 번호로 적절한 등수 찾는지 확인")
    void valueOfTest() {
        assertThat(LottoRank.valueOf(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.valueOf(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.valueOf(5, false)).isEqualTo(LottoRank.THIRD);
    }
}