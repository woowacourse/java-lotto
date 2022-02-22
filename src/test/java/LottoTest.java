import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 번호를 생성한다.")
    void generateNumber() {
        Lotto lotto = new Lotto();

        assertThat(lotto.generateNumber().size()).isEqualTo(6);
    }
}
