package lotto.domain;

import java.util.List;
import lotto.exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("숫자가 6개가 아닌 경우 예외 발생")
    void generateByString_checkSize() {
        Assertions.assertThatThrownBy(() -> Lotto.generateByManual(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 중복되는 경우 예외 발생")
    void generateByString_checkDuplicate() {
        Assertions.assertThatThrownBy(() -> Lotto.generateByManual(List.of(1 ,1, 2, 3, 4, 5)))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }
}
