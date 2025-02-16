package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {
    
    @ParameterizedTest
    @MethodSource("duplicateLottoRandomNumbers")
    @DisplayName("로또_번호가_중복되는_경우_예외_발생")
    public void 로또_번호가_중복되는_경우_예외_발생(List<Integer> duplicateNumbers) {
        assertThatThrownBy(() -> {
            new Lotto(duplicateNumbers);
        }).isInstanceOf(LottoException.class);
    }

    private static Stream<Arguments> duplicateLottoRandomNumbers() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 5))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLottoNumbers")
    @DisplayName("로또_번호는_6개여야합니다")
    public void 로또_번호는_6개여야합니다(List<Integer> invalidSizeNumbers) {
        assertThatThrownBy(() -> {
            new Lotto(invalidSizeNumbers);
        }).isInstanceOf(LottoException.class);
    }

    private static Stream<Arguments> invalidLottoNumbers() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4))
        );
    }

    @ParameterizedTest
    @MethodSource("lottoNumbers")
    @DisplayName("사용자가_구매한_로또_내역을_정렬하여_출력한다")
    public void 사용자가_구매한_로또_내역을_정렬하여_출력한다(List<Integer> lottoNumbers) {
        assertThat(new Lotto(lottoNumbers).buyNumber())
                .isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    private static Stream<Arguments> lottoNumbers() {
        return Stream.of(
                Arguments.arguments(List.of(6, 5, 4, 3, 2, 1))
        );
    }
}
