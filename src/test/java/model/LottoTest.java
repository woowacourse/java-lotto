package model;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import view.OutputView;

class LottoTest {

    @Test
    void 로또_정렬_확인_테스트() {
        Lotto lottoOne = new Lotto(Arrays.asList(1, 4, 5, 13, 32, 39));
        Lotto lottoTwo = new Lotto(Arrays.asList(39, 32, 13, 5, 4, 1));

        List<Integer> arrayLottoOneResult = lottoOne.getLottoNumber();
        List<Integer> arrayLottoTwoResult = lottoTwo.getLottoNumber();

        Assertions.assertThat(arrayLottoOneResult).isEqualTo(arrayLottoTwoResult);
    }

    @Test
    void 로또_출력_확인_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1, 4, 5, 13, 32, 39));
        String expect = "[1,4,5,13,32,39]";

        String result = OutputView.printLottoNumber(lotto);

        Assertions.assertThat(result).isEqualTo(expect);
    }
}