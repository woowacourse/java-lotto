package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinnerLottoTest {
    private static final LottoNumber BONUS = new LottoNumber(7);
    private static final Lotto LOTTO = new Lotto(givenNumbers(1, 2, 3, 4, 5, 6));
    private static final WinnerLotto WINNER_LOTTO = new WinnerLotto(LOTTO, BONUS);

    @ParameterizedTest(name = "로또번호 : {0}, 결과 : {1}")
    @MethodSource("lottoNumbersAndRank")
    @DisplayName("맞춘 번호에 따라 등수를 반환한다.")
    void findRank(Lotto lotto, Rank rank) {
        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(rank);
    }

    @Test
    @DisplayName("보너스볼은 당첨 번호와 중복되면 예외를 발생한다.")
    void throwExceptionWhenDuplicated() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new WinnerLotto(LOTTO, new LottoNumber(6)))
            .withMessageMatching("보너스볼은 당첨번호와 중복될 수 없다.");
    }

    private static List<LottoNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    }

    static Stream<Arguments> lottoNumbersAndRank() {
        return Stream.of(
            Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 5, 6)), Rank.FIRST),
            Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 5, 7)), Rank.SECOND),
            Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 5, 9)), Rank.THIRD),
            Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 4, 9, 10)), Rank.FOURTH),
            Arguments.arguments(new Lotto(givenNumbers(1, 2, 3, 8, 9, 10)), Rank.FIFTH),
            Arguments.arguments(new Lotto(givenNumbers(1, 2, 8, 9, 10, 11)), Rank.NONE)
        );
    }
}
