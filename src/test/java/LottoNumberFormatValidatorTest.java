import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberFormatValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "1,   2,  3   , 4,5,6"})
    @DisplayName("올바른 로또 번호 포맷 검증")
    void validateValidLottoNumberFormat(String lottoNumbers) {
        LottoNumberFormatValidator validator = new LottoNumberFormatValidator();

        assertThatCode(() -> validator.validate(lottoNumbers))
            .doesNotThrowAnyException();
    }
}
