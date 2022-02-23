package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PickedNumberTest {
    @Test
    @DisplayName("숫자 6개를 뽑았는가")
    void Generate_6_Number() {
        PickedNumbers pickedNumbers = new PickedNumbers();
        Assertions.assertThat(pickedNumbers.getPickedNumbers().stream().count()).isEqualTo(6);
    }

    @Test
    @DisplayName("모든 숫자가 1~45 사이에 위치하는가")
    void Is_Between_1_To_45() {
        PickedNumbers pickedNumbers = new PickedNumbers();
        Assertions.assertThat(pickedNumbers.getPickedNumbers().stream().allMatch(i -> i >= 1 && i <= 45)).isTrue();
    }
}
