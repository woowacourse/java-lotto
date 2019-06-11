package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    @Test
    void 잘_못_생성되었을_때_테스트() {
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create("1,2,3,4,5,6"),"123");
        assertThat(winningLotto.isNotCreatedWell()).isTrue();
    }
}
