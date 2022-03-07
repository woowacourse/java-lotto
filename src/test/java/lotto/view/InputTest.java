package lotto.view;

import lotto.domain.Ball;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.view.Input.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputTest {

    @Test
    @DisplayName("구입 금액에 null 을 입력할 경우")
    void payment_null() {
        assertThatThrownBy(() -> {
            Entering entering = () -> null;
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액에 빈값을 입력했을 경우")
    void payment_empty() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "";
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액에 문자를 입력했을 경우")
    void payment_not_number() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "payment";
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 로또 개수에 null 을 입력할 경우")
    void manual_count_null() {
        assertThatThrownBy(() -> {
            Entering entering = () -> null;
            int manualCount = inputManualCount(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 로또 개수에 빈값을 입력했을 경우")
    void manual_count_empty() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "";
            int manualCount = inputManualCount(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 로또 개수에 문자를 입력했을 경우")
    void manual_count_not_number() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "manualCount";
            int manualCount = inputManualCount(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 null 을 입력했을 경우")
    void win_number_null() {
        assertThatThrownBy(() -> {
            Entering entering = () -> null;
            List<Ball> winNumber = inputLottoNumbers(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 빈값을 입력했을 경우")
    void win_number_empty() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "";
            List<Ball> winNumber = inputLottoNumbers(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 문자를 입력했을 경우")
    void win_number_not_number() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "1번, 2번, 3번, 4번, 5번, 6번";
            List<Ball> winNumber = inputLottoNumbers(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자에 null 을 입력했을 경우")
    void ball_null() {
        assertThatThrownBy(() -> {
            Entering entering = () -> null;
            int ball = inputBonusBall(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자에 빈값을 입력했을 경우")
    void ball_empty() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "";
            int ball = inputBonusBall(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자에 문자를 입력했을 경우")
    void ball__not_number() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "ball";
            int ball = inputBonusBall(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}