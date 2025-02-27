package domain;

import generator.DefaultLottoNumber;
import generator.FixedLottoNumber;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 생성 테스트")
    void createLottoTest() {

        //given & when
        DefaultLottoNumber defaultLottoNumber = new DefaultLottoNumber();
        Lotto lotto = new Lotto(defaultLottoNumber);
        List<Integer> numbers = lotto.getNumbers();
        //then
        Assertions.assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 비교 테스트")
    void compareLottoTest() {

        //given
        List<Integer> sampleWinningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(sampleWinningNumber, bonusNumber);

        FixedLottoNumber fixedLottoNumber = new FixedLottoNumber(List.of(1, 2, 3, 4, 5, 6));
        //when
        Lotto lotto = new Lotto(fixedLottoNumber);
        LottoMatch lottoMatch = winningLotto.compareLotto(lotto);
        //then
        Assertions.assertThat(lottoMatch).isEqualTo(LottoMatch.SIX_MATCH);
    }


}
