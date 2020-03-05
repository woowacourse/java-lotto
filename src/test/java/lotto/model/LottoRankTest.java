package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoRankTest {
    @Test
    @DisplayName("세개 미만 숫자 맞췄을 경우 ture 반환")
    void checkNoPrize() {
        assertTrue(LottoRank.checkNoPrize(2));
        assertFalse(LottoRank.checkNoPrize(3));
    }

    @ParameterizedTest
    @CsvSource(value = {"FIRST, 1, 2000000000",
            "SECOND, 2, 60000000",
            "THIRD, 3, 450000",
            "FOURTH, 4, 200000",
            "FIFTH, 5, 25000"})
    @DisplayName("맞은 갯수에 따른 총 상금 계산")
    void prizeResult(LottoRank lottoRank, int value, int result) {
        assertThat(lottoRank.prizeResult(value)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"FIRST, 6, false",
            "SECOND, 5, true",
            "THIRD, 5, false",
            "FOURTH, 4, false",
            "FIFTH, 3, false"})
    @DisplayName("맞은 갯수에 따른 총 상금 계산")
    void of(LottoRank lottoRank, int matchNumber, boolean hasBonusNumber) {
        assertThat(LottoRank.of(matchNumber, hasBonusNumber)).isEqualTo(lottoRank);
    }

}
