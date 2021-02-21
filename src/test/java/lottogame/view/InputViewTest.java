package lottogame.view;

import lottogame.utils.InvalidMoneyException;
import lottogame.utils.InvalidWinningLottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

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

    @DisplayName("올바른 당첨 로또 생성 확인")
    @Test
    void 당첨_로또_입력() {
        InputStream in = new ByteArrayInputStream("1, 2, 3, 4, 5, 6".getBytes());
        System.setIn(in);
        List<Integer> numbers = InputView.inputWinningLottoNumbers();
        numbers.contains(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("구분자가 맨 앞에 들어가는 경우 예외 처리")
    @Test
    void 잘못된_당첨_로또_입력() {
        InputStream in = new ByteArrayInputStream(", 1, 2, 3, 4, 5, 6".getBytes());
        System.setIn(in);
        assertThrows(InvalidWinningLottoException.class, () -> InputView.inputWinningLottoNumbers());
    }

    @DisplayName("구분자가 맨 뒤에 들어가는 경우 예외 처리")
    @Test
    void 잘못된_당첨_로또_입력2() {
        InputStream in = new ByteArrayInputStream("1, 2, 3, 4, 5, 6,".getBytes());
        System.setIn(in);
        assertThrows(InvalidWinningLottoException.class, () -> InputView.inputWinningLottoNumbers());
    }

    @DisplayName("공백이 중간에 들어가는 경우 예외 처리")
    @Test
    void 잘못된_당첨_로또_입력3() {
        InputStream in = new ByteArrayInputStream("1, 2, 3,    4, 5, 6,".getBytes());
        System.setIn(in);
        assertThrows(InvalidWinningLottoException.class, () -> InputView.inputWinningLottoNumbers());
    }
}