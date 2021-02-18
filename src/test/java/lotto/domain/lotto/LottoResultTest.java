package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.view.OutputView;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    void 로또_결과_초기화_테스트() {
        LottoResult lottoResult = new LottoResult();

        System.out.println(lottoResult.getResult().toString());
    }

    @Test
    void 로또_일치_갯수_테스트() {
        Lotto testLotto = Lotto.generatedBy(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto testLotto2 = Lotto.generatedBy(Arrays.asList(6, 7, 3, 9, 10, 11));

        int count = (int) testLotto.getNumbers().stream()
            .filter(number -> testLotto2.getNumbers().contains(number)).count();

        assertThat(count).isEqualTo(2);
    }

    @Test
    void 로또_당첨_카운팅_테스트() {
        Lotto testLotto = Lotto.generatedBy(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.generatedBy(testLotto, LottoNumber.valueOf(10));

        LottoResult lottoResult = new LottoResult();
        lottoResult.add(testLotto, winningLotto);
        LottoRank lottoRank = lottoResult.findRank(6, true);
        assertThat(lottoResult.getResult().get(lottoRank)).isEqualTo(1);

        lottoResult.add(testLotto, winningLotto);
        assertThat(lottoResult.getResult().get(lottoRank)).isEqualTo(2);

    }

    @Test
    void 로또_수익률_최고_테스트() {
        Lotto testLotto = Lotto.generatedBy(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.generatedBy(testLotto, LottoNumber.valueOf(10));
        LottoResult lottoResult = new LottoResult();

        lottoResult.add(testLotto, winningLotto);
        System.out.println(lottoResult.getResult());
        System.out.println(lottoResult.getProfitRate());
    }

    @Test
    void 로또_수익률_일반_테스트() {

        Lotto testLotto = Lotto.generatedBy(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto testLotto2 = Lotto.generatedBy(Arrays.asList(4, 5, 6, 7, 8, 9));
        WinningLotto winningLotto = WinningLotto.generatedBy(testLotto2, LottoNumber.valueOf(10));
        LottoResult lottoResult = new LottoResult();
        lottoResult.add(testLotto, winningLotto);
        System.out.println(lottoResult.getResult());
        System.out.println(lottoResult.getProfitRate());

    }

    @Test
    void 수익률_내림_테스트() {
        System.out.println(Math.floor((double) 5000 / 14000 * 100) / 100);
    }

    @Test
    void 출력_문구_테스트() {
        LottoResult lottoResult = new LottoResult();
        OutputView.numMatchPrint(lottoResult);
    }
}
