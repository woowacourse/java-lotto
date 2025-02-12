import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 당첨번호와_보너스번호가_중복되는_경우_예외() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(1);

        // when & then
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}