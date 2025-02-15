package model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 입력_개수와_생성되는_로또_수가_일치하는지_확인한다() {
        final int count = 5;
        Lottos lottos = new Lottos(count);

        assertThat(lottos.getLottos().size())
                .isEqualTo(count);
    }
}