package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerLottoTest {
    @DisplayName("보너스 숫자는 중복되거나 1~45 범위 외일 수 없다")
    @Test
    void test() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new AnswerLotto(lotto, 6));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new AnswerLotto(lotto, 46));
    }
}