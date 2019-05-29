package lotto.view.inputview;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceParserTest {
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
        assertThrows(NumberFormatException.class, () -> {
            PriceParser.getLottoAmount("a");
        });
    }

    @Test
    void 음수일_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceParser.getLottoAmount("-1");
        });
    }

    @Test
    void 판매_단위가_아닌경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceParser.getLottoAmount("1001");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PriceParser.getLottoAmount("10010");
        });
    }

    @Test
    void 최소_1000원이상() {
        assertThrows(IllegalArgumentException.class, () -> {
           PriceParser.getLottoAmount("0");
        });
    }

    @Test
    void 생성될_로또_개수() {
        assertThat(PriceParser.getLottoAmount("1000")).isEqualTo(1);
    }

    @Test
    void 금액_마지막_원_허용() {
        int answer = PriceParser.getLottoAmount("1000");
        assertThat(answer).isEqualTo(PriceParser.getLottoAmount("1000원"));
    }
}