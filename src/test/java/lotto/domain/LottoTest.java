package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("로또 구매개수 입력이 정상일 때")
    void 로또_구매_개수_판별() {
        Lotto lotto = new Lotto(14000);
        assertThat(lotto.getTicketCount()).isEqualTo(14);
    }
}
