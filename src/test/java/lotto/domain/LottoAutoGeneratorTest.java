package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoAutoGeneratorTest {

    @Test
    void 자동생성된_로또_유효성_검사() {
        assertDoesNotThrow(() -> AutoLottoGenerator.generateAutoLotto());
    }
}
