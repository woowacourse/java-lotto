package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {
    @DisplayName("입력받은 값이 숫자가 아니면 예외처리")
    @Test
    void 로또구매금입력시_예외처리() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("test".getBytes()));
        InputView inputView = new InputView(scanner);

        assertThatThrownBy(inputView::takeLottoMoney).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 구분자로 구분되어 있는 숫자인지 확인")
    @Test
    void 당첨번호가_구분자로_구분된_숫자인지_확인() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("test,test".getBytes()));
        InputView inputView = new InputView(scanner);

        assertThatThrownBy(inputView::inputWinningNumbers).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
