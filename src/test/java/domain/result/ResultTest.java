package domain.result;

import domain.lotto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ResultTest {

    @DisplayName("Result객체 정상 생성 테스트")
    @Test
    void Result_객체_정상_생성된다() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lottos lottos = new Lottos(Collections.singletonList(new Lotto(LottoNumbers.generate(lottoNumbers))));
        assertThatCode(() -> new Result(lottos))
                .doesNotThrowAnyException();
    }

    @DisplayName("Result 결과 반환한다.")
    @Test
    void Result_결과를_반환한다() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lottos lottos = new Lottos(Collections.singletonList(new Lotto(LottoNumbers.generate(lottoNumbers))));

        Result result = new Result(lottos);
        WinningLotto winningLotto = new WinningLotto(LottoNumbers.generate(Arrays.asList(1, 2, 3, 4, 5, 6)), BonusNumber.of(7));

        Map<LottoRank, Integer> results =  result.findMatches(winningLotto);

        assertThat(results.get(LottoRank.SIX_MATCHES)).isEqualTo(1);
    }

    @DisplayName("Result 복수 결과 반환한다")
    @Test
    void Result_로또_복수_결과_테스트() {
        List<Integer> lottoNumbers1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lottoNumbers2 = Arrays.asList(1, 2, 3, 4, 5, 8);
        List<Lotto> lottos = new ArrayList<Lotto>();
        lottos.add(new Lotto(LottoNumbers.generate(lottoNumbers1)));
        lottos.add(new Lotto(LottoNumbers.generate(lottoNumbers2)));
        Lottos multipleLottos = new Lottos(lottos);

        Result result = new Result(multipleLottos);
        WinningLotto winningLotto = new WinningLotto(LottoNumbers.generate(Arrays.asList(1, 2, 3, 4, 5, 6)), BonusNumber.of(7));

        Map<LottoRank, Integer> results =  result.findMatches(winningLotto);

        assertThat(results.get(LottoRank.SIX_MATCHES)).isEqualTo(1);
        assertThat(results.get(LottoRank.FIVE_MATCHES)).isEqualTo(1);
    }
}