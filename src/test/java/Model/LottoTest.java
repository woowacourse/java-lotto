package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_생성_테스트 () throws Exception {
        Lotto lotto = new Lotto();
        List<Integer> testLotto = lotto.getLottoNumber();

        Assertions.assertThat(testLotto.size()).isEqualTo(6);
        for (int a : testLotto){
            Assertions.assertThat(a).isBetween(1, 45);
        }
    }

}