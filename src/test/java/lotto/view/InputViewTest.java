package lotto.view;

import lotto.model.exception.WinnerNumbersInputFormException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputViewTest {
        @Test
        void 당첨_번호_입력형식_검사() {
                assertThrows(WinnerNumbersInputFormException.class, () -> {
                        ByteArrayInputStream input = new ByteArrayInputStream("1, 2, 3, 4, 5, 6,".getBytes());
                        System.setIn(input);
                        InputView.inputWinnerNumbers();
                });
        }

        @Test
        void 구매_금액_정수_검사() {
                assertThrows(NumberFormatException.class, () -> {
                        ByteArrayInputStream input = new ByteArrayInputStream("15,000".getBytes());
                        System.setIn(input);
                        InputView.inputPayment();
                });
        }
}
