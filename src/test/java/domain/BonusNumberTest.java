package domain;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    @DisplayName("보너스 넘버 생성을 정상적으로 성공한다")
    void createBonusNumberTest() {
        //given
        int number1 = 1;
        int number2 = 45;
        //when
        BonusNumber bonusNumber1 = new BonusNumber(number1);
        BonusNumber bonusNumber2 = new BonusNumber(number2);
        //then
        assertAll(() -> assertThat(bonusNumber1.getValue()).isEqualTo(number1),
                () -> assertThat(bonusNumber2.getValue()).isEqualTo(number2));
    }
}
