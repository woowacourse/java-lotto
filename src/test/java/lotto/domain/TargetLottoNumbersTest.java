package lotto.domain;

import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TargetLottoNumbersTest {
    @ParameterizedTest
    @DisplayName("로또 숫자 6개를 받아 당첨 번호와 보너스 번호를 비교해 당첨 종류를 반환한다.")
    @MethodSource("provideLottoNumbersAndMatchKind")
    void getLottoMatchResult(final List<String> numbers, final LottoMatchKind expected) {
        //given
        final LottoNumbers targetNumbers = new LottoNumbers(Arrays.asList("2", "3", "4", "5", "6", "7"));
        final LottoNumber bonusNumber = LottoNumber.ONE;
        final TargetLottoNumbers targetLottoNumbers = new TargetLottoNumbers(targetNumbers, bonusNumber);
        final LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        //when
        final LottoMatchKind actual = targetLottoNumbers.getLottoMatchResult(lottoNumbers);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideLottoNumbersAndMatchKind() {
        return Stream.of(
                Arguments.of(Arrays.asList("1", "2", "3", "4", "5", "6"), LottoMatchKind.FIVE_BONUS),
                Arguments.of(Arrays.asList("2", "3", "4", "5", "6", "7"), LottoMatchKind.SIX),
                Arguments.of(Arrays.asList("3", "4", "5", "6", "7", "8"), LottoMatchKind.FIVE),
                Arguments.of(Arrays.asList("4", "5", "6", "7", "8", "9"), LottoMatchKind.FOUR),
                Arguments.of(Arrays.asList("5", "6", "7", "8", "9", "10"), LottoMatchKind.THREE)
        );
    }
}
