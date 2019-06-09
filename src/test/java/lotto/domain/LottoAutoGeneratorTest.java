package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoAutoGeneratorTest {

    @Test
    void 자동생성된_로또_유효성_검사() {
        // Lotto 생성자를 통해 유효성 검사
        assertDoesNotThrow(() -> new UserLotto(AutoLottoGenerator.generateAutoLotto()));
    }

    @Test
    void aaak() {
        assertThat(AutoLottoGenerator.generateAutoLotto().size()).isEqualTo(6);
    }
}
