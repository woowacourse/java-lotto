package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("문자열로 조회된 LottoNumber 내부에 정확한 값이 들어있는지 확인")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(strings = {"1", "20", "45"})
    void of_ValidStringInput_Successful(String value) {
        LottoNumber lottoNumber = LottoNumber.of(value);

        assertThat(lottoNumber.getNumber()).isEqualTo(Integer.parseInt(value));
    }

    @DisplayName("숫자가 아닌 문자열 입력 시 예외 발생")
    @Test
    void validateAndParseNumber_throwIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoNumber.of("!"))
                .withMessageMatching("로또 번호는 숫자여야 합니다.");
    }

    @DisplayName("조회된 LottoNumber 내부에 정확한 값이 들어있는지 확인")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(ints = {1, 20, 45})
    void of_ValidInteger_Successful(int number) {
        LottoNumber lottoNumber = LottoNumber.of(number);

        assertThat(lottoNumber.getNumber()).isEqualTo(number);
    }

    @DisplayName("범위 내 숫자 주입 시 조회 성공")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(ints = {1, 20, 45})
    void of_ValidRangeInput_Successful(int number) {
        assertThatNoException()
                .isThrownBy(() -> LottoNumber.of(number));
    }

    @DisplayName("범위 외 숫자 주입 시 예외 발생")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(ints = {0, 46})
    void of_InvalidRangeInput_Fail(int number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoNumber.of(number))
                .withMessageMatching("1과 45 사이의 숫자를 입력해야 합니다.");
    }

    @DisplayName("모든 로또 번호가 존재하는지 확인")
    @Test
    void getAllLottoNumbers_ContainsAllNumbers_Successful() {
        List<LottoNumber> allLottoNumbers = LottoNumber.getAllLottoNumbers();

        assertThat(allLottoNumbers.size()).isEqualTo(45);
    }
}
