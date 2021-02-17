package lotto.view;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    @DisplayName("로또 대금을 입력했을 때 숫자가 아니면 예외처리")
    @Test
    void 로또_대금_구매시_숫자아닌값_입력(){
        Scanner scanner = new Scanner(new ByteArrayInputStream("test".getBytes()));
        InputView inputView = new InputView(scanner);

        assertThatThrownBy(inputView::inputDecimal).isInstanceOf(NoSuchElementException.class);
    }
}