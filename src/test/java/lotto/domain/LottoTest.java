package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    @DisplayName("Lotto 생성자 테스트")
    @Test
    void lotto_constructor_test() {
        Lotto lotto = new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @DisplayName("Lotto 생성자 6개가 아닌 숫자 입력 예외 테스트")
    @Test
    void lotto_constructor_error_not_six_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7))))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("Lotto 생성자 숫자 중복 예외 테스트")
    @Test
    void lotto_constructor_error_on_duplication_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(new ArrayList<>(Arrays.asList(2, 2, 3, 4, 5, 6))))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("Lotto 생성자 숫자 범위 예외 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {-1, 46})
    void lotto_constructor_error_on_range_test(int errorNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(new ArrayList<>(Arrays.asList(errorNumber, 2, 3, 4, 5, 6))))
                .withMessage("로또 숫자 범위는 1 ~ 45입니다.");
    }
}
