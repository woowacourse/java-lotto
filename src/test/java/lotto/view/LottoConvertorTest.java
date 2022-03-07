package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.exception.InvalidLottoSizeException;
import lotto.model.exception.InvalidNumberRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoConvertorTest {

    private LottoConvertor parser;

    @BeforeEach
    void setUp() {
        parser = new LottoConvertor();
    }

    @Test
    @DisplayName("로또 당첨 번호 분리")
    void splitWinningNumber() {
        assertThat(parser.convert("1,2,3,4,5,6")).isEqualTo(Lotto.create(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    @DisplayName("로또 당첨 번호 공백 제거 분리")
    void splitWinningNumberWithTrim() {
        assertThat(parser.convert("1, 2,3,4 ,5,    6"))
            .isEqualTo(Lotto.create(List.of(1, 2, 3, 4, 5, 6)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    @DisplayName("잘못된 로또 번호 크기 검")
    void validateInvalidLottoNumberSize(String invalidLottoNumbers) {
        assertThatThrownBy(() -> parser.convert(invalidLottoNumbers))
            .isInstanceOf(InvalidLottoSizeException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100,2,3,4,5,6", "46, 1, 2, 3, 4, 5", "0,1,2,3,4,5"})
    @DisplayName("잘못된 로또 번호 범위 검증")
    void validateInvalidLottoNumberRange(String invalidLottoNumbers) {
        assertThatThrownBy(() -> parser.convert(invalidLottoNumbers))
            .isInstanceOf(InvalidNumberRangeException.class);
    }
}
