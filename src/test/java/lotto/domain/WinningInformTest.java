package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_DUPLICATE_WINNING_AND_BONUS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningInformTest {

    @DisplayName("정상적으로 객체가 생성된다.")
    @Test
    void test_WinningInform() {
        Lotto winningLotto = new Lotto(List.of(1,2,3,4,5,6));
        int bonus = 7;

        WinningInform winningInform = new WinningInform(winningLotto, bonus);
        assertThat(winningInform).isNotNull();
    }

    @DisplayName("보너스 번호가 당첨번호와 중복된다면 예외가 발생한다.")
    @Test
    void test_bonusDuplicate() {
        Lotto winningLotto = new Lotto(List.of(1,2,3,4,5,6));
        int bonus = 6;

        assertThatThrownBy(() -> {
            new WinningInform(winningLotto, bonus);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_DUPLICATE_WINNING_AND_BONUS);
    }

}