package lotto.controller;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InputControllerTest {

    private final InputController inputController = new InputController();

    @Test
    void 입력한_금액을_개수로_변환1() {
        String value = "10000";
        assertThat(inputController.countLotto(value)).isEqualTo(10);
    }

    @Test
    void 입력한_금액을_개수로_변환2() {
        String value = "1500";
        assertThat(inputController.countLotto(value)).isEqualTo(1);
    }

    @Test
    void 당첨번호_분리_성공() {
        String value = "1, 2,3, 4, 5,6";
        assertThat(inputController.splitWinningNumbers(value)).contains(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 보너스번호_테스트() {
        String value = "44";
        assertThat(inputController.toIntBonusNumber(value, List.of(1, 2, 3, 4, 5, 6))).isEqualTo(44);
    }
}
