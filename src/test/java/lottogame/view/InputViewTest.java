package lottogame.view;

import lottogame.utils.InvalidMoneyException;
import lottogame.utils.InvalidWinningLottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {

    @DisplayName("로또 금액이 문자인 경우 예외 처리")
    @Test
    void 로또_구매_금액_입력() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("가나다".getBytes()));
        InputView inputView = new InputView(scanner);
        assertThrows(InvalidMoneyException.class,
                () -> inputView.inputMoney());
    }

    @DisplayName("올바른 당첨 로또 생성 확인")
    @Test
    void 당첨_로또_입력() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("1, 2, 3, 4, 5, 6".getBytes()));
        InputView inputView = new InputView(scanner);
        List<Integer> numbers = inputView.inputWinningLottoNumbers();
        assertThat(numbers).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("구분자가 맨 앞에 들어가는 경우 예외 처리")
    @Test
    void 잘못된_당첨_로또_입력() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(", 1, 2, 3, 4, 5, 6".getBytes()));
        InputView inputView = new InputView(scanner);
        assertThrows(InvalidWinningLottoException.class, () -> inputView.inputWinningLottoNumbers());
    }

    @DisplayName("구분자가 맨 뒤에 들어가는 경우 예외 처리")
    @Test
    void 잘못된_당첨_로또_입력2() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("1, 2, 3, 4, 5, 6,".getBytes()));
        InputView inputView = new InputView(scanner);
        assertThrows(InvalidWinningLottoException.class, () -> inputView.inputWinningLottoNumbers());
    }

    @DisplayName("공백이 중간에 들어가는 경우 예외 처리")
    @Test
    void 잘못된_당첨_로또_입력3() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("1, 2, 3,    4, 5, 6,".getBytes()));
        InputView inputView = new InputView(scanner);
        assertThrows(InvalidWinningLottoException.class, () -> inputView.inputWinningLottoNumbers());
    }
}