package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }
}
