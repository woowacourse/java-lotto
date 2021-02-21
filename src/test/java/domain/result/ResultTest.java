package domain.result;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ResultTest {

    @DisplayName("Result객체 정상 생성 테스트")
    @Test
    void Result_객체_정상_생성된다() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        Lottos lottos = new Lottos(Collections.singletonList(new Lotto(new LottoBalls(lottoBalls))));

        //then
        assertThatCode(() -> new Result(lottos))
                .doesNotThrowAnyException();
    }

    @DisplayName("Result 결과 반환한다.")
    @Test
    void Result_결과를_반환한다() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int[] winningLottoNumbers = {1, 2, 3, 4, 5, 6};
        int bonusNumber = 7;

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        Lottos lottos = new Lottos(Collections.singletonList(new Lotto(new LottoBalls(lottoBalls))));
        Result result = new Result(lottos);

        Set<Integer> winningNumbers = Arrays.stream(winningLottoNumbers)
                .boxed()
                .collect(Collectors.toSet());

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Map<LottoRank, Integer> results = result.findMatches(winningLotto);

        //then
        assertThat(results.get(LottoRank.SIX_MATCHES)).isEqualTo(1);
    }

    @DisplayName("Result 복수 결과 반환한다")
    @Test
    void Result_로또_복수_결과_테스트() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lottoNumbers2 = Arrays.asList(1, 2, 3, 4, 5, 8);
        int[] winningLottoNumbers = {1, 2, 3, 4, 5, 6};
        int bonusNumber = 7;

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        List<LottoBall> lottoBalls2 = lottoNumbers2.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        Lottos lottos = new Lottos(Arrays.asList(new Lotto(new LottoBalls(lottoBalls)), new Lotto(new LottoBalls(lottoBalls2))));
        Result result = new Result(lottos);

        Set<Integer> winningNumbers = Arrays.stream(winningLottoNumbers)
                .boxed()
                .collect(Collectors.toSet());

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Map<LottoRank, Integer> results = result.findMatches(winningLotto);

        assertThat(results.get(LottoRank.SIX_MATCHES)).isEqualTo(1);
        assertThat(results.get(LottoRank.FIVE_MATCHES)).isEqualTo(1);
    }

    @DisplayName("5개의 볼, 보너스 볼이 맞을 때 2등 당첨된다.")
    @Test
    void ResultSecondPrizeTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        int[] winningLottoNumbers = {1, 2, 3, 4, 5, 6};
        int bonusNumber = 7;

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        Lottos lottos = new Lottos(Collections.singletonList(new Lotto(new LottoBalls(lottoBalls))));
        Result result = new Result(lottos);

        Set<Integer> winningNumbers = Arrays.stream(winningLottoNumbers)
                .boxed()
                .collect(Collectors.toSet());

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Map<LottoRank, Integer> results = result.findMatches(winningLotto);

        //then
        assertThat(results.get(LottoRank.FIVE_AND_BONUS_MATCHES)).isEqualTo(1);
    }
}