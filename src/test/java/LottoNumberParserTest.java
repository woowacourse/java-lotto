import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberParserTest {

    @Test
    @DisplayName("로또 당첨 번호 분리")
    void splitWinningNumber() {
        LottoNumberParser parser = new LottoNumberParser();

        assertThat(parser.parse("1, 2, 3, 4, 5, 6")).contains(1, 2, 3, 4, 5, 6);
    }

}
