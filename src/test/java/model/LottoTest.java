package model;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_생성_테스트() {
        Lotto lotto = new Lotto();
        List<Integer> testLotto = lotto.getLottoNumber();

        Assertions.assertThat(testLotto).hasSize(6);
        for (int a : testLotto) {
            Assertions.assertThat(a).isBetween(1, 45);
        }
    }

    @Test
    void 로또_확인() {
        List<Integer> lottoNumbers = new ArrayList<>(List.of(6, 5, 4, 3, 2, 1));
        Lotto lotto = new Lotto(lottoNumbers);
        String result = lotto.printLottoNumber();
        Assertions.assertThat(result).isEqualTo("[1,2,3,4,5,6]");
    }
}