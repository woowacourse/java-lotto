package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    void Lottos_생성_테스트_정상() {
        Lottos lottos = new Lottos(new Money(2000));
        assertThat(lottos.size()).isEqualTo(2);
    }
}
