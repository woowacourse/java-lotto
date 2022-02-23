import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberValidatorTest {
    private static LottoNumberValidator lottoNumberValidator;

    @BeforeEach
    void setUp() {
        lottoNumberValidator = new LottoNumberValidator();
    }

    @ParameterizedTest
    @DisplayName("로또 번호가 1 ~ 45 사이에 있는 경우 테스트")
    @ValueSource(ints = {1, 45})
    void checkValidLottoNumberRangeTest(int lottoNumber) {
        assertThatCode(() -> lottoNumberValidator.validate(lottoNumber))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호 리스트가 1 ~ 45 사이에 있는 경우 테스트")
    void checkValidLottoNumbersRangeTest() {
        assertThatCode(() -> lottoNumberValidator.validate(List.of(1, 2, 3, 4, 5, 45)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호 리스트가 1 ~ 45 사이에 있지 않은 경우 테스트")
    void checkInvalidLottoNumbersRangeTest() {
        assertThatThrownBy(() -> lottoNumberValidator.validate(List.of(1, 2, 3, 0, 5, 46)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.");
    }
}
