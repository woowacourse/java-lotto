import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberParserTest {

    @Test
    @DisplayName("로또 당첨 번호 분리")
    void splitWinningNumber() {
        LottoNumberParser parser = new LottoNumberParser();

        assertThat(parser.parse("1, 2, 3, 4, 5, 6")).contains(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("6개 이하 당첨 번호 분리 실패")
    void splitWinningNumberUnderSix() {
        LottoNumberParser parser = new LottoNumberParser();

        assertThatThrownBy(() -> parser.parse("1, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("6개 이상 당첨 번호 분리 실패")
    void splitWinningNumberOverSix() {
        LottoNumberParser parser = new LottoNumberParser();

        assertThatThrownBy(() -> parser.parse("1, 2, 3, 4, 5, 6, 7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }
}
