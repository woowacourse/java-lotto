package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {

    @Test
    @DisplayName("로또 개수 객체에 제대로 값이 들어가는 지 테스트")
    public void init() {
        LottoCount count = new LottoCount(5);
        assertThat(count.get()).isEqualTo(5);
    }
}
