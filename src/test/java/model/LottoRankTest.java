package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoRankTest {

    static Stream<Arguments> provideMatchCountAndBonusMatch() {
        return Stream.of(
                Arguments.of(6, false, LottoRank.FIRST),
                Arguments.of(5, true, LottoRank.SECOND),
                Arguments.of(5, false, LottoRank.THIRD),
                Arguments.of(4, true, LottoRank.FOURTH),
                Arguments.of(3, true, LottoRank.FIFTH),
                Arguments.of(2, true, LottoRank.NOTHING),
                Arguments.of(1, true, LottoRank.NOTHING)
        );
    }

    @Test
    @DisplayName("음수 일치 갯수로 생성 시 예외 발생")
    void createByNegativeMatchCount() {
        assertThatThrownBy(() -> LottoRank.of(-1, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.");
    }

    @Test
    @DisplayName("일치 갯수가 6 초과되는 값으로 생성 시 예외 발생")
    void createByOverSixMatchCount() {
        assertThatThrownBy(() -> LottoRank.of(7, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("로또 등수 판별 테스트")
    @MethodSource("provideMatchCountAndBonusMatch")
    void createValidLottoRank(int matchCount, boolean bonusMatch, LottoRank rank) {

    }
}
