package Model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoCreatorTest {
    @Test
    void 로또_번호_생성_테스트() throws Exception {

        List<Integer> testLotto = LottoCreator.createLotto();

        Assertions.assertThat(testLotto.size()).isEqualTo(6);
        for (int lottoNumber : testLotto) {
            Assertions.assertThat(lottoNumber).isBetween(1, 45);
        }
    }
}