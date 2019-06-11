package lottogame.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 생성이_제대로_안됐을_때_테스트() {
        Lotto lotto = ManualLottoGenerator.create("1,1,1,1,1");
        assertThat(lotto.isNotCreatedWell()).isTrue();
    }
}
