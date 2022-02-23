package view;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.LottoNumberFormatValidator;

public class LottoNumberFormatValidatorTest {
    private LottoNumberFormatValidator validator;

    @BeforeEach
    void setUp() {
        validator = new LottoNumberFormatValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "1,   2,  3   , 4,5,6"})
    @DisplayName("올바른 로또 번호 포맷 검증")
    void validateValidLottoNumberFormat(String lottoNumbers) {
        assertThatCode(() -> validator.validate(lottoNumbers))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"100,200,3,4,5,6", "1,2,3,4,5"})
    @DisplayName("잘못된 로또 번호 포맷 검증")
    void validateInvalidLottoNumberFormat(String invalidLottoNumbers) {
        assertThatThrownBy(() -> validator.validate(invalidLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoNumberFormatValidator.INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }
}
