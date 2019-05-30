package lotto.view;

import lotto.view.inputview.PriceParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceParserTest {
    @Test
    void 입력이_없을_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceParser.getPurchasePrice("");
        });
    }

    @Test
    void null_입력() {
        assertThrows(NullPointerException.class, () -> {
            PriceParser.getPurchasePrice(null);
        });
    }

    @Test
    void 숫자가_아닐_경우() {
        assertThrows(NumberFormatException.class, () -> {
            PriceParser.getPurchasePrice("a");
        });
    }
    @Test
    void 금액_마지막_원_허용() {
        Integer answer = PriceParser.getPurchasePrice("1000");
        assertThat(answer).isEqualTo(PriceParser.getPurchasePrice("1000원"));
    }
}