package lotto.view.inputview;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningNumbersParserTest {
    @Test
    void 입력이_없을_경우() {
        assertThrows(NullPointerException.class, () -> {
            PriceParser.getLottoAmount("");
        });
    }

    @Test
    void 쉼표_기준으로_구분() {
    }

    @Test
    void 정수가_아닌_번호_존재() {
    }

    @Test
    void 유효한_범위_번호() {
    }

    @Test
    void 번호_개수() {
        
    }

    @Test
    void 중복된_번호() {
    }
}