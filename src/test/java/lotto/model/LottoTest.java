package lotto.model;

import static lotto.constant.LottoNumberConstants.*;
import static lotto.model.fixture.LottoNumberFixture.generateLottoNumbersInRange;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @DisplayName("중복 없는 6개의 숫자로 로또를 생성할 수 있다.")
    @Test
    void ok_NotDuplicatedNumbers() {
        Set<LottoNumber> lottoNumbers = generateLottoNumbersInRange(1, 6);

        assertDoesNotThrow(() -> new Lotto(lottoNumbers));
    }

    @DisplayName("로또 숫자가 6개가 아닌 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNumberCountIsNot6() {
        Set<LottoNumber> lottoNumbers = generateLottoNumbersInRange(1, 3);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Lotto(lottoNumbers),
                String.format("로또 번호는 %d개여야 합니다.", LOTTO_NUMBER_COUNT.value())
        );
    }

    @DisplayName("특정 번호를 가지고 있는지 확인할 수 있다.")
    @ParameterizedTest
    @MethodSource("lottoNumberAndIsMatchResult")
    void containsTest(LottoNumber number, boolean result) {
        Set<LottoNumber> lottoNumbers = generateLottoNumbersInRange(1, 6);
        Lotto lotto = new Lotto(lottoNumbers);

        Assertions.assertThat(lotto.contains(number)).isEqualTo(result);
    }

    @DisplayName("로또를 비교할 수 있다.")
    @ParameterizedTest
    @MethodSource("lottoNumbersAndMatchCounts")
    void matchTest(int count, Set<LottoNumber> lottoNumbers1, Set<LottoNumber> lottoNumbers2) {
        Lotto lotto1 = new Lotto(lottoNumbers1);
        Lotto lotto2 = new Lotto(lottoNumbers2);

        assertEquals(count, lotto1.getMatchCount(lotto2));
    }

    private static Stream<Arguments> lottoNumberAndIsMatchResult() {
        return Stream.of(
                Arguments.of(new LottoNumber(1), true),
                Arguments.of(new LottoNumber(7), false)
        );
    }

    private static Stream<Arguments> lottoNumbersAndMatchCounts() {
        return Stream.of(
                Arguments.of(1, generateLottoNumbersInRange(1, 6), generateLottoNumbersInRange(6, 11)),
                Arguments.of(2, generateLottoNumbersInRange(1, 6), generateLottoNumbersInRange(5, 10)),
                Arguments.of(3, generateLottoNumbersInRange(1, 6), generateLottoNumbersInRange(4, 9)),
                Arguments.of(4, generateLottoNumbersInRange(1, 6), generateLottoNumbersInRange(3, 8)),
                Arguments.of(5, generateLottoNumbersInRange(1, 6), generateLottoNumbersInRange(2, 7)),
                Arguments.of(6, generateLottoNumbersInRange(1, 6), generateLottoNumbersInRange(1, 6))
        );
    }
}
