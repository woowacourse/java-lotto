package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    @Test
    @DisplayName("입력받은 보너스번호가 숫자가 아니면 예외처리")
    void Is_Number_Format() {
        assertThatThrownBy(() -> new BonusNumber("a", new ChoiceNumber())).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46"})
    @DisplayName("입력받은 보너스번호가 범위내에 있는지")
    void Number_In_Range(String value) {
        assertThatThrownBy(() -> new BonusNumber(value, new ChoiceNumber())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력받은 당첨번호와 보너스넘버가 중복되는 값이 있는지")
    void Duplicate_Number_Exist() {
        assertThatThrownBy(() -> new BonusNumber("5", new ChoiceNumber("1,3,5,7,9,11"))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("조건에 부합할 때 정상적으로 보너스번호가 생성되는지 확인")
    void Bonus_Number_Generation_Test() {
        String input = "5";
        BonusNumber bonusNumber = new BonusNumber(input, new ChoiceNumber("1,2,3,4,6,7"));
        int number = bonusNumber.getBonusNumber();
        Assertions.assertThat(number).isEqualTo(Integer.parseInt(input));

    }
}
