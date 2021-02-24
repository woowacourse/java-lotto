package domain.result;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ResultTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void initWinningLotto() {
        int[] winningNumber = {1, 2, 3, 4, 5, 6};
        int bonusNumber = 7;
        Set<Integer> winningNumbers = Arrays.stream(winningNumber)
                .boxed()
                .collect(Collectors.toSet());
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    @DisplayName("Result 정상 생성 테스트.")
    @Test
    void resultGenerateTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        LottoTickets lottoTickets = new LottoTickets(Collections.singletonList(new LottoTicket(new LottoBalls(lottoBalls))));

        //then
        assertThatCode(() -> new Result(lottoTickets, winningLotto))
                .doesNotThrowAnyException();
    }

    @DisplayName("Result 결과 테스트.")
    @Test
    void resultFindRankTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        LottoTickets lottoTickets = new LottoTickets(Collections.singletonList(new LottoTicket(new LottoBalls(lottoBalls))));
        Result result = new Result(lottoTickets, winningLotto);

        Map<LottoRank, Integer> results = result.getResults();

        //then
        assertThat(results.get(LottoRank.SIX_MATCHES)).isEqualTo(1);
    }

    @DisplayName("Result 복수 결과 반환 테스트.")
    @Test
    void resultManyRankTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lottoNumbers2 = Arrays.asList(1, 2, 3, 4, 5, 8);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        List<LottoBall> lottoBalls2 = lottoNumbers2.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(new LottoBalls(lottoBalls)), new LottoTicket(new LottoBalls(lottoBalls2))));
        Result result = new Result(lottoTickets, winningLotto);

        Map<LottoRank, Integer> results = result.getResults();

        assertThat(results.get(LottoRank.SIX_MATCHES)).isEqualTo(1);
        assertThat(results.get(LottoRank.FIVE_MATCHES)).isEqualTo(1);
    }

    @DisplayName("5개의 볼, 보너스 볼이 맞을 때 2등 당첨된다.")
    @Test
    void resultSecondPrizeTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        LottoTickets lottoTickets = new LottoTickets(Collections.singletonList(new LottoTicket(new LottoBalls(lottoBalls))));
        Result result = new Result(lottoTickets, winningLotto);

        Map<LottoRank, Integer> results = result.getResults();

        //then
        assertThat(results.get(LottoRank.FIVE_AND_BONUS_MATCHES)).isEqualTo(1);
    }

    @DisplayName("수익률 반환 테스트.")
    @Test
    void resultEarningsRateTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 7, 8, 9);
        List<Integer> lottoNumbers2 = Arrays.asList(1, 2, 3, 7, 8, 9);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        List<LottoBall> lottoBalls2 = lottoNumbers2.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(new LottoBalls(lottoBalls)), new LottoTicket(new LottoBalls(lottoBalls2))));
        Result result = new Result(lottoTickets, winningLotto);

        BigDecimal earningsRate = result.findEarningsRate(new BettingMoney(2000));
        System.out.println("earningsRate.doubleValue() = " + earningsRate.doubleValue());
        assertThat(earningsRate).isEqualTo(new BigDecimal("5.00"));
    }
}