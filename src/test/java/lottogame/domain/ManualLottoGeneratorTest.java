package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoGeneratorTest {
    @Test
    void 정상적으로_생성되는지_테스트() {
        assertThat(ManualLottoGenerator.create("1,2,3,4,5,6")).isExactlyInstanceOf(Lotto.class);
    }

    @Test
    void 중복되는_숫자가_존재할_경우_생성이_안되는지_테스트() {
        assertThat(ManualLottoGenerator.create("1,2,3,4,5,5").isNotCreatedWell()).isTrue();
    }

    @Test
    void 정상적인_입력이_아닐_경우_생성이_안되는지_테스트() {
        assertThat(ManualLottoGenerator.create("asdf").isNotCreatedWell()).isTrue();
    }
}
