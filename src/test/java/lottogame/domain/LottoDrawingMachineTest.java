package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoDrawingMachineTest {

    @Test
    @DisplayName("당첨 번호를 문자로 만들려고 할때")
    void insertString() {
        assertThatThrownBy(
            () -> new LottoDrawingMachine().drawing("a, b, c, 1, 2, 3")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 번호를 입력했을 때")
    void insertDuplication() {
        assertThatThrownBy(
            () -> new LottoDrawingMachine().drawing("1, 1, 1, 2, 2, 2")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("6개 이외의 당첨번호를 입력했을 때")
    void insertInvalidCount() {
        assertThatThrownBy(
            () -> new LottoDrawingMachine().drawing("1, 1, 1,")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백을 입력했을 때")
    void insertBlank() {
        assertThatThrownBy(
            () -> new LottoDrawingMachine().drawing(" ,")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new LottoDrawingMachine().drawing("")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new LottoDrawingMachine().drawing(" ")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new LottoDrawingMachine().drawing(",")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
