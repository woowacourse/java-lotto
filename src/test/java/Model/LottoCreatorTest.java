package Model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoCreatorTest {
    @Test
    void 로또_번호_범위_테스트() throws Exception {

        List<Integer> testLotto = LottoCreator.createLotto();
        int lottoMinNumber = 1;
        int lottoMaxNumber = 45;

        for (int lottoNumber : testLotto) {
            Assertions.assertThat(lottoNumber).isBetween(lottoMinNumber, lottoMaxNumber);
        }
    }

    @Test
    void 로또_번호_생성_개수_확인_테스트(){
        List<Integer> testLotto = LottoCreator.createLotto();
        int expect=  6;

        int lottoSize = testLotto.size();

        Assertions.assertThat(lottoSize).isEqualTo(expect);
    }
}