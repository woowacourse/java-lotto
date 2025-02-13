package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {
    @Test
    @DisplayName("보너스 넘버가 1부터 45사이가 아니므로 예외가 발생한다")
    void validateRangeTest() {
        //given
    	int number1 = 0;
        int number2 = 46;
        //when
        //then
        assertAll(() -> assertThatThrownBy(() -> new BonusNumber(number1)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(()->new BonusNumber(number2)).isInstanceOf(IllegalArgumentException.class));
    }
}
