package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    @Test
    public void 로또_6자리_번호_리스트_생성_테스트() {
        assertThat(LottoFactory.create().size()).isEqualTo(6);
    }
}
