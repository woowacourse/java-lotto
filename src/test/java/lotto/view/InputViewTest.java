package lotto.view;

import lotto.exception.ConvertFailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {

    @DisplayName("베팅금액이 비어있는 경우 Exception 발생")
    @ParameterizedTest
    @ValueSource(strings = {" ", "  "})
    void inputBettingMoney1(String bettingMoney) {
        //given
        InputStream input = new ByteArrayInputStream(bettingMoney.getBytes());
        InputView inputView = new InputView(new Scanner(input));

        //then
        assertThatThrownBy(inputView::inputBettingMoney)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값이 비어있습니다.");
    }

    @DisplayName("베팅금액 정상 반환")
    @Test
    void inputBettingMoney2() {
        //given
        String bettingMoney = "10000";
        int result = 10000;

        InputStream input = new ByteArrayInputStream(bettingMoney.getBytes());
        InputView inputView = new InputView(new Scanner(input));

        //when
        int expect = inputView.inputBettingMoney();

        //then
        assertThat(expect).isEqualTo(result);
    }

    @DisplayName("입력받은 우승번호가 비어있는 경우")
    @ParameterizedTest
    @ValueSource(strings = {" ", "  "})
    void inputWinningNumber(String winningNumber) {
        //given
        //given
        InputStream input = new ByteArrayInputStream(winningNumber.getBytes());
        InputView inputView = new InputView(new Scanner(input));

        //then
        assertThatThrownBy(inputView::inputWinningNumber)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값이 비어있습니다.");
    }

    @DisplayName("입력받은 우승번호 반환")
    @Test
    void inputWinningNumber() {
        //given
        String winningNumber = "1,2,3,4,5,6";
        InputStream input = new ByteArrayInputStream(winningNumber.getBytes());
        InputView inputView = new InputView(new Scanner(input));

        //when
        Set<Integer> expect = inputView.inputWinningNumber();

        //then
        assertThat(expect).contains(1, 2, 3, 4, 4, 5, 6);
    }

    @DisplayName("입력받은 보너스 번호가 숫자가 아닌 문자일 경우")
    @Test
    void inputBonusNumber1() {
        //given
        String bonusNumber = "a";
        InputStream input = new ByteArrayInputStream(bonusNumber.getBytes());
        InputView inputView = new InputView(new Scanner(input));

        //then
        assertThatThrownBy(inputView::inputBonusNumber)
                .isInstanceOf(ConvertFailException.class)
                .hasMessage("%s : 잘못된 숫자 입력", bonusNumber);
    }

    @DisplayName("입력받은 보너스 번호 정상 반환")
    @Test
    void inputBonusNumber2() {
        //given
        String bonusNumber = "1";
        InputStream input = new ByteArrayInputStream(bonusNumber.getBytes());
        InputView inputView = new InputView(new Scanner(input));

        //when
        int expect = inputView.inputBonusNumber();

        //then
        assertThat(expect).isEqualTo(1);
    }
}