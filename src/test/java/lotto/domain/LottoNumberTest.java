package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("입력받은 보너스번호가 범위내에 없을 때 예외처리")
    void Number_In_Range(int value) {
        assertThatThrownBy(() -> new LottoNumber(value)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @Test
    @DisplayName("조건에 부합할 때 정상적으로 보너스번호가 생성되는지 확인")
    void Lotto_Number_Generation_Test() {
        int inputValue = 5;
        LottoNumber lottoNumber = new LottoNumber(inputValue);
        int number = lottoNumber.getNumber();
        Assertions.assertThat(number).isEqualTo(inputValue);

    }
}
