package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    @DisplayName("로또번호 확인")
    @Test
    void correctBonusNumber(){
        String number = "11";

        BonusNumber bonusNum = new BonusNumber(number);

        assertThat(bonusNum).isEqualTo(new BonusNumber("11"));
    }

    @DisplayName("숫자인지 확인")
    @Test
    void isNumber(){
        String input = "aaa";

        assertThatThrownBy(()->{
            new BonusNumber(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 숫자여야 합니다.");
    }

    @DisplayName("보너스 볼 생성시 1~45 사이인지 확인")
    @Test
    void validateBonusNumber(){
        String input = "46";

        assertThatThrownBy(()->{
            new BonusNumber(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45 사이의 숫자여야 합니다.");

    }
}
