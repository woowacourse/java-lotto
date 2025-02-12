package Model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_생성_테스트 () throws Exception {
        Lotto lotto = new Lotto(LottoCreator.createLotto());
        List<Integer> testLotto = lotto.getLottoNumber();

        Assertions.assertThat(testLotto.size()).isEqualTo(6);
        for (int a : testLotto){
            Assertions.assertThat(a).isBetween(1, 45);
        }
    }

    @Test
    void 로또_확인(){
        Lotto lotto = new Lotto(List.of(1,13,5,4,32,39));
        String result = lotto.printLottoNumber();
        Assertions.assertThat(result).isEqualTo("[1,4,5,13,32,39]");
    }
}