package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @DisplayName("로또 묶음을 생성한다.")
    @Test
    void 로또_묶음을_생성한다() {

        //given
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoBundle lottoBundle = lottoGenerator.makeLottoBundle(10);

        //when & then
        assertThat(lottoBundle.getLottoQuantity()).isEqualTo(10);
    }

    @DisplayName("당첨 번호를 생성한다.")
    @Test
    void 당첨_번호를_생성한다() {

        //given
        LottoGenerator lottoGenerator = new LottoGenerator();
        Lotto lotto = new Lotto(
                Set.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5),
                        LottoNumber.of(6)));
        WinningNumbers winningNumbers = new WinningNumbers(lotto, LottoNumber.of(8));
        WinningNumbers madeWinningNumbers = lottoGenerator.makeWinningNumbers("1,2,3,4,5,6", "8");

        //when
        int firstWinningNumberCount = winningNumbers.checkMatchCount(lotto);
        int secondWinningNumberCount = madeWinningNumbers.checkMatchCount(lotto);

        //then
        assertThat(firstWinningNumberCount).isEqualTo(secondWinningNumberCount);
    }

}
