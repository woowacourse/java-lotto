import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    @DisplayName("일치하는 번호 개수 확인 테스트")
    public void checkMatchNumber() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int winning = lotto.match(List.of(1, 2, 3, 4, 5, 6));

        assertThat(winning).isEqualTo(6);
    }
}
