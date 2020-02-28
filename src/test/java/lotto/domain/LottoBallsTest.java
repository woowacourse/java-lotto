package lotto.domain;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBallsTest {
    @Test
    @DisplayName("6개의 숫자가 아닌 다른 개수의 숫자가 들어왔을 경우 테스트")
    void incorrect_six_number() {
        assertThatThrownBy(() -> LottoBalls.generateLottoBalls("1,2,3,4"))
                .isInstanceOf(NumberOutOfRangeException.class);
    }

    @Test
    @DisplayName("수동 생성 로또 볼이 중복 입력 되었을 경우 테스트")
    void incorrect_manual_lotto_ticket_by_not_number_test() {
        assertThatThrownBy(() -> LottoBalls.generateLottoBalls("1,1,2,3,4,5"))
                .isInstanceOf(DuplicationException.class)
                .hasMessage("중복값이 존재합니다.");
    }
}
