package lotto.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    @DisplayName("입력받은 값이 숫자가 아니면 예외처리")
    @Test
    void 숫자아닌값_입력시_예외처리() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("test".getBytes()));
        InputView inputView = new InputView(scanner);

        assertThatThrownBy(inputView::inputDecimal).isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("당첨 번호가 구분자로 구분되어 있는 숫자인지 확인")
    @Test
    void 구분자로_구분된_숫자인지_확인() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("test,test".getBytes()));
        InputView inputView = new InputView(scanner);

        assertThatThrownBy(inputView::inputToIntegerList).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}
