package lotto.domain;

import static lotto.domain.LottoRank.*;

import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoRankTest {

    @Test
    @DisplayName("맵을 초기화 할 수 있는가?")
    void Init_Lotto_Rank_Map() {
        Map<LottoRank, Integer> lottoRankIntegerMap = initLottoRankMap();
        Assertions.assertThat(lottoRankIntegerMap.keySet()).containsExactly(LottoRank.values());
    }

    @ParameterizedTest
    @DisplayName("등수를 올바르게 결정하는지 확인")
    @MethodSource("rankParameterProvider")
    void Get_Proper_Rank_For_Condition(int sameCount, boolean bonus, LottoRank expectedLottoRank) {
        LottoRank lottoRank = LottoRank.valueOf(sameCount, bonus);
        Assertions.assertThat(lottoRank).isEqualTo(expectedLottoRank);
    }

    private static Stream<Arguments> rankParameterProvider() {
        return Stream.of(
                Arguments.arguments(6, false, FIRST),
                Arguments.arguments(5, true, SECOND),
                Arguments.arguments(5, false, THIRD),
                Arguments.arguments(4, false, FOURTH),
                Arguments.arguments(3, false, FIFTH),
                Arguments.arguments(0, false, NOTHING)
        );
    }
}
