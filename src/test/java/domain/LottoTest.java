package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {

    private static Stream<Arguments> twoLottosForMatchedEachOther() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(40, 41, 42, 43, 44, 45)), 0),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 43, 44, 45)), 3),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6));
    }

    @ParameterizedTest
    @MethodSource("twoLottosForMatchedEachOther")
    void matchedEachOther_rightResult(Lotto lotto, Lotto otherLotto, int expected) {
        assertThat(lotto.matchedEachOther(otherLotto)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"20,true", "45,false"}, delimiter = ',')
    void lotto_hasBonusBall(int bonusNumber, boolean expected) {
        Lotto lotto = new Lotto(List.of(10, 15, 20, 25, 30, 35));
        LottoBall bonusBall = new LottoBall(bonusNumber);
        assertThat(lotto.hasBall(bonusBall)).isEqualTo(expected);
    }

}
