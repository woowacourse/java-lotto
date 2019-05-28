package lotto.view;

import lotto.model.WinnerNumbersInputFormException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputViewTest {
        @Test
        void 당첨_번호_입력형식_검사() {
                assertThrows(WinnerNumbersInputFormException.class, ()->{
                        ByteArrayInputStream input = new ByteArrayInputStream("1, 2, 3, 4, 5, 6,".getBytes());
                        System.setIn(input);
                        InputView.inputWinnerNumbers();
                });
        }
}
