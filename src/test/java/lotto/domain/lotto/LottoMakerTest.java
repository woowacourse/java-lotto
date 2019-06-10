package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMakerTest {
    @Test
    void 수동_로또_생성_순서_정렬() {
        Lotto lotto = LottoMaker.generator(new Numbers(Arrays.asList(6,5,4,3,2,1)));
        assertThat(lotto).isEqualTo(Lotto.of(new Numbers(Arrays.asList(1,2,3,4,5,6))));
    }

    @Test
    void 자동_로또_생성_번호_수_확인() {
        Lotto lotto = LottoMaker.generator();
        assertThat(lotto.getLottoNumbers().size()).isEqualTo(6);
    }
}
