package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {

    @Test
    @DisplayName("우승 티켓 검증 테스트")
    void findLottoResult() {
        assertThat(LottoResult.findLottoResult(6, false)).isEqualTo(LottoResult.FIRST);
        assertThat(LottoResult.findLottoResult(5, true)).isEqualTo(LottoResult.SECOND);
        assertThat(LottoResult.findLottoResult(5, false)).isEqualTo(LottoResult.THIRD);
        assertThat(LottoResult.findLottoResult(4, false)).isEqualTo(LottoResult.FOURTH);
        assertThat(LottoResult.findLottoResult(3, false)).isEqualTo(LottoResult.FIFTH);
        assertThat(LottoResult.findLottoResult(2, false)).isEqualTo(LottoResult.FAILED);
        assertThat(LottoResult.findLottoResult(1, false)).isEqualTo(LottoResult.FAILED);
        assertThat(LottoResult.findLottoResult(0, false)).isEqualTo(LottoResult.FAILED);
    }
}