package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumbersTest {
    @ParameterizedTest
    @MethodSource("duplicateLottoRandomNumbers")
    @DisplayName("로또_번호가_중복되는_경우_예외_발생")
    public void 로또_번호가_중복되는_경우_예외_발생(List<Integer> duplicateNumbers){
        assertThatThrownBy(() -> {
            new LottoNumbers(duplicateNumbers);
        }).isInstanceOf(LottoException.class);
    }

    private static Stream<Arguments> duplicateLottoRandomNumbers(){
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 5))
        );
    }



    @ParameterizedTest
    @MethodSource("invalidLottoNumbers")
    @DisplayName("로또_번호는_6개여야합니다")
    public void 로또_번호는_6개여야합니다(List<Integer> invalidSizeNumbers){
        assertThatThrownBy(() -> {
            new LottoNumbers(invalidSizeNumbers);
        }).isInstanceOf(LottoException.class);
    }

    private static Stream<Arguments> invalidLottoNumbers(){
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4))
        );
    }
}
