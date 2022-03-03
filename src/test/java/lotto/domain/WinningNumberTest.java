package lotto.domain;

import static lotto.domain.LottoRank.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumberTest {

    @ParameterizedTest
    @DisplayName("올바른 등수를 판별하는지")
    @MethodSource("rankProvider")
    void Decide_Rank(List<Integer> inputValues, LottoRank inputRank) {
        ChoiceNumber choiceNumber = new ChoiceNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7, choiceNumber);
        WinningNumber winningNumber = new WinningNumber(choiceNumber, bonusNumber);

        Assertions.assertThat(winningNumber.findLottoRank(new ChoiceNumber(inputValues))).isEqualTo(inputRank);
    }

    private static Stream<Arguments> rankProvider() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6), FIRST),
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 7), SECOND),
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 8), THIRD),
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 9, 7), FOURTH),
                Arguments.arguments(Arrays.asList(1, 2, 3, 11, 12, 13), FIFTH)
        );
    }
}
