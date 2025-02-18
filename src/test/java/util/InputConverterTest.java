package util;

import static org.assertj.core.api.Assertions.*;

import exception.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputConverter;

class InputConverterTest {
    @DisplayName("정상 숫자 반환 테스트")
    @ParameterizedTest
    @ValueSource(strings = {" 1"," 1 ","1"})
    void convertNumberTest(String input){
        assertThat(InputConverter.convertNumber(input)).isEqualTo(1);
    }

    @DisplayName("숫자 반환 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a"," 1a ","1 1"})
    void convertNumberExceptionTest(String input){
        assertThatThrownBy(()->InputConverter.convertNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_TYPE_ERROR.getMessage());
    }

    @DisplayName("정상 숫자 리스트 반환 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6","1, 2, 3, 4, 5, 6 "," 1, 2, 3, 4, 5, 6"," 1, 2, 3, 4, 5, 6 "})
    void convertWinningLottoTest(String input){
        // when
        List<Integer> numbers = InputConverter.convertWinningLotto(input);
        // then
        assertThat(numbers).containsExactly(1,2,3,4,5,6);
    }

    @DisplayName("숫자 리스트 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, a","1, 2,3, 4, 5, 6 "," 1. 2, 3, 4, 5, 6","1, 2, 3, 4, 5, 6, 7"})
    void convertWinningLottoExceptionTest(String input){
        assertThatThrownBy(()->InputConverter.convertWinningLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_FORMAT_ERROR.getMessage());
    }
}