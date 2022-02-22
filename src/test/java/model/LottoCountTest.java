package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoCountTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings ={"","  ","\t","\n"})
    @DisplayName("투입 금액 공백 검증")
    public void validateLottoNumber(String number) {
        assertThatThrownBy(() -> new LottoCount(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
