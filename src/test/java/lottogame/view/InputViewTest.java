package lottogame.view;

import lottogame.utils.InvalidMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    @DisplayName("로또 금액이 문자인 경우 예외 처리")
    @Test
    void 로또_구매_금액_입력() {
        InputStream in = new ByteArrayInputStream("가나다".getBytes());
        System.setIn(in);
        assertThrows(InvalidMoneyException.class,
                () -> InputView.inputMoney());
    }
}