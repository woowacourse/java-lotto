package lotto.view.inputview;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class priceParserTest {
    @Test
    void 입력이_없을_경우() {
        assertThrows(NullPointerException.class, () -> {
            PriceParser.getLottoAmount("");
        });
    }

    @Test
    void null_입력() {
        assertThrows(NullPointerException.class, () -> {
            PriceParser.getLottoAmount(null);
        });
    }

    @Test
    void 숫자가_아닐_경우() {
    }

    @Test
    void 음수일_경우() {
    }

    @Test
    void 판매_단위가_아닌경우() {
    }

    @Test
    void 생성될_로또_개수() {
    }
}