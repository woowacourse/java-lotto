package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    @DisplayName("숫자가 6개인지 확인")
    void validateSixNumbers() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Lotto.NUMBER_COUNT_ERROR);
    }

    @Test
    @DisplayName("숫자가 중복인지 확인")
    void validateOverlappedNumber() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Lotto.NUMBER_DUPLICATE_ERROR);
    }

    @Test
    @DisplayName("숫자가 범위에 있는지 확인")
    void validateNumberRange() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 50));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Lotto.NUMBER_RANGE_ERROR);
    }
}