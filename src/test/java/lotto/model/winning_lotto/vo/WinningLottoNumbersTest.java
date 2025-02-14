package lotto.model.winning_lotto.vo;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoNumbersTest {

    private static Stream<Arguments> 생성_시_번호가_중복되면_예외_발생_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 5)
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 4, 5)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("생성_시_번호가_중복되면_예외_발생_테스트_케이스")
    void 생성_시_번호가_중복되면_예외_발생(List<Integer> numbers) {

        assertThatIllegalArgumentException().isThrownBy(() -> WinningLottoNumbers.from(numbers));
    }
}