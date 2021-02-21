package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private static final String NUMBER_COUNT_ERROR = "[ERROR] 6개의 숫자를 입력해주세요";
    private static final String NUMBER_DUPLICATE_ERROR = "[ERROR] 숫자는 중복될 수 없습니다";
    private static final String NUMBER_RANGE_ERROR = "[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요";

    @Test
    @DisplayName("숫자가 6개인지 확인")
    void validateSixNumbers() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_COUNT_ERROR);
    }

    @Test
    @DisplayName("숫자가 중복인지 확인")
    void validateOverlappedNumber() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_DUPLICATE_ERROR);
    }

    @Test
    @DisplayName("숫자가 범위에 있는지 확인")
    void validateNumberRange() {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 50));
        assertThatThrownBy(() -> {
            new Lotto(nums);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NUMBER_RANGE_ERROR);
    }
}