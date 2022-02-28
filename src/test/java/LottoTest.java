import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 확인")
    void lotto_generate() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getLottoNumbers()).containsExactly(1, 2, 3, 4, 5, 6);

    }

}
