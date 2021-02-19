package lottogame.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lottogame.domain.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWinNumberIssueMachineTest {

    @Test
    @DisplayName("당첨 번호를 문자로 만들려고 할때")
    void insertString() {
        assertThatThrownBy(
            () -> LottoWinNumberIssueMachine.issueWinNumbers("a, b, c, 1, 2, 3")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 번호를 입력했을 때")
    void insertDuplication() {
        assertThatThrownBy(
            () -> LottoWinNumberIssueMachine.issueWinNumbers("1, 1, 1, 2, 2, 2")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("6개 이외의 당첨번호를 입력했을 때")
    void insertInvalidCount() {
        assertThatThrownBy(
            () -> LottoWinNumberIssueMachine.issueWinNumbers("1, 1, 1,")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백을 입력했을 때")
    void insertBlank() {
        assertThatThrownBy(
            () -> LottoWinNumberIssueMachine.issueWinNumbers(" ,")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> LottoWinNumberIssueMachine.issueWinNumbers("")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> LottoWinNumberIssueMachine.issueWinNumbers(" ")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> LottoWinNumberIssueMachine.issueWinNumbers(",")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호 생성")
    void drawingBonus() {
        LottoNumber bonusNumber = LottoWinNumberIssueMachine.issueBonusNumber("3");
        assertThat(bonusNumber).isEqualTo(LottoWinNumberIssueMachine.issueBonusNumber("3"));
        assertThat(bonusNumber).isEqualTo(new LottoNumber("3"));
    }
}