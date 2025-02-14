package lotto.model.winning_lotto;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    private static Stream<Arguments> 당첨_번호가_보너스_번호를_포함하면_예외가_발생한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 6
                ),
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6), 5
                )
        );
    }

    @ParameterizedTest
    @MethodSource("당첨_번호가_보너스_번호를_포함하면_예외가_발생한다_테스트_케이스")
    void 당첨_번호가_보너스_번호를_포함하면_예외가_발생한다(List<Integer> winningLottoNumbers, int bonusNumber) {

        assertThatIllegalArgumentException().isThrownBy(() -> WinningLotto.of(winningLottoNumbers, bonusNumber));
    }
}