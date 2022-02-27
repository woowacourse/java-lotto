package lotterymachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LotteryNumberTest {

    @Test
    @DisplayName("1~45 범위에 벗어난 숫자를 입력 시, 에러 발생")
    void validateNumber() {
        Assertions.assertThatThrownBy(() -> {
            LotteryNumber lotteryNumber = new LotteryNumber(0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이의 값이어야 합니다.");
    }



}