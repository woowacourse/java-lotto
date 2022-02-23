package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import lotto.controller.LottoController;
import lotto.view.InputView;
import org.junit.jupiter.api.Test;

class LottoWinningNumbersTest {

    @Test
    public void 당첨번호_입력_검증() {
        String value = InputView.inputLottoWinningNumbers();

//        assertThat(value).isEqualTo(Set.copyOf(testValues).size());
    }
}