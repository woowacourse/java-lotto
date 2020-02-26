package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualLottoNumbersTest {

    @Test
    @DisplayName("숫자가 아닌 값을 판단하는 테스트")
    void validateNumberFormat() {
        assertThatThrownBy(() -> {
            new ManualLottoNumbers("1, 2, 3, 4, a, 5");
        }).isInstanceOf(NotNumberException.class)
            .hasMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("6개를 입력을 안했을 때 오류메시지")
    void validateLottoNumbersLength() {
        assertThatThrownBy(() -> {
            new ManualLottoNumbers("1, 3, 5, 7, 9, 11, 13");
        }).isInstanceOf(NotSixNumbersException.class)
            .hasMessage("숫자 6개를 입력해주세요.");
    }
}
