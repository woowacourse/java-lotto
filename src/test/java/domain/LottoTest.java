package domain;

import static error.ErrorMessage.INVALID_DUPLICATE_NUMBER;
import static error.ErrorMessage.INVALID_NUMBERS_SIZE;
import static error.ErrorMessage.INVALID_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import net.bytebuddy.build.ToStringPlugin.Enhance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
    private static Stream<Arguments> methodSourceIterableTestArguments() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5)),
                Arguments.arguments(List.of()),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        );
    }

    @ParameterizedTest
    @MethodSource("methodSourceIterableTestArguments")
    @DisplayName("숫자가 6개가 아닐 경우 예외를 발생시킨다.")
    void 갯수가_6이_아닌_경우(List<Integer> values) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto lotto = Lotto.from(values);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_NUMBERS_SIZE.getMessage());
    }

    @DisplayName("숫자가 6개인 경우 성공")
    @Test
    void 성공적으로_생성_되었을_경우() {
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.from(expectedNumbers);
        List<Integer> lottoNumbers = lotto.getNumbers();
        assertEquals(expectedNumbers, lottoNumbers);
    }

    @DisplayName("중복되는 숫자를 입력할 경우 예외를 발생시킨다. ")
    @Test
    void 숫자가_중복되는_경우() {
        List<Integer> expectedNumbers = List.of(1, 1, 2, 3, 4, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto lotto = Lotto.from(expectedNumbers);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_DUPLICATE_NUMBER.getMessage());
    }

    @DisplayName("1~45 사이의 숫자일 경우, 테스트를 통과한다.")
    @Test
    void 숫자_범위가_유효한_경우() {
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 45);
        Lotto lotto = Lotto.from(expectedNumbers);
        List<Integer> numbers = lotto.getNumbers();
        assertEquals(expectedNumbers, numbers);
    }

    @DisplayName("1~45 사이 숫자가 아닐 경우 예외를 발생시킨다.")
    @Test
    void 숫자_범위가_유효하지_않은_경우() {
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 46);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto lotto = Lotto.from(expectedNumbers);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_NUMBER_RANGE.getMessage());
    }

    @DisplayName("로또 번호와 당첨 번호의 일치 갯수를 비교한다.")
    @ParameterizedTest
    @MethodSource("comparingTestCases")
    void 로또_번호와_당첨_번호의_일치_갯수를_비교한다(List<Integer> lottoNumbers, List<Integer> winningNumbers, int expectedCount){
        Lotto lotto = Lotto.from(lottoNumbers);
        Lotto winningLotto = Lotto.from(winningNumbers);

        assertEquals(expectedCount, lotto.calculateMatchCount(winningLotto));
    }

    private static Stream<Arguments> comparingTestCases(){
        return Stream.of(
                Arguments.arguments( List.of(1, 2, 3, 4, 5, 6),List.of(1,2,3,4,5,6),6),
                Arguments.arguments(List.of(1, 2, 7, 8, 9, 10), List.of(1, 2, 3, 4, 5, 6), 2),
                Arguments.arguments(List.of(7, 8, 9, 10, 11, 12), List.of(1, 2, 3, 4, 5, 6), 0)
        );
    }
}
