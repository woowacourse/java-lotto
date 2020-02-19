package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    @DisplayName("로또번호 중복검사")
    void validateDuplication() {
        List<Integer> input1 = Arrays.asList(1, 2, 3, 4, 5, 5);
        List<Integer> input2 = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> Lotto.validateDuplication(input1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatCode(() -> Lotto.validateDuplication(input2))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 숫자 6개인지 검사")
    void validateSizeTest() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        assertThatThrownBy(() -> Lotto.validateSize(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 숫자의 범위 유효성 확인")
    void validateNumberScopeTest() {
        List<Integer> input1 = Arrays.asList(0, 1, 2, 3, 4, 5);
        List<Integer> input2 = Arrays.asList(1, 2, 3, 4, 5, 46);
        assertThatThrownBy(() -> Lotto.validateNumbersScope(input1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Lotto.validateNumbersScope(input2))
                .isInstanceOf(IllegalArgumentException.class);

    }

}
