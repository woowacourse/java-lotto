package lotto.domain;

import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    public static final List<Integer> WINNING_NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6);
    public static final int BONUS_NUMBER = 7;

    private static Stream<Arguments> provideWinningNumberAndBonusNumber() {
        return Stream.of(
            Arguments.of(Arrays.asList(2, 4, 8, 9, 13, 25), "NONE"),
            Arguments.of(Arrays.asList(2, 4, 6, 8, 13, 25), "FIFTH"),
            Arguments.of(Arrays.asList(2, 4, 6, 1, 7, 3), "SECOND"),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), "FIRST")
        );
    }

    @ParameterizedTest
    @DisplayName("로또 매칭 확인")
    @MethodSource("provideWinningNumberAndBonusNumber")
    void test(List<Integer> lottoNumbers, String lottoRank) {
        Lotto lotto = new Lotto(lottoNumbers);
        LottoRank rank = lotto.getLottoRank(WINNING_NUMBERS, BONUS_NUMBER);
        assertThat(rank.name()).isEqualTo(lottoRank);
    }
}
