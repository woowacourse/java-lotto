package lotterymachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LotteryNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1~45 범위에 벗어난 숫자를 입력 시, 에러 발생")
    void validateNumber(int input) {
        Assertions.assertThatThrownBy(() -> {
                    LotteryNumber lotteryNumber = LotteryNumber.valueOf(input);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이의 값이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 45})
    @DisplayName("1~45 범위의 숫자를 입력 받아 생성한다.")
    void create(int input) {
        LotteryNumber lotteryNumber = LotteryNumber.valueOf(input);
        assertThat(lotteryNumber.getNumber()).isEqualTo(input);
    }
}