package domain.lotto;

import domain.ball.LottoBall;
import domain.ball.LottoBalls;
import domain.result.LottoRank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class LottoTicketsTest {

    List<LottoTicket> lottoTickets = new ArrayList<>();
    WinningLotto winningLotto;

    @BeforeEach
    void init() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> lottoNumbers2 = Arrays.asList(5, 6, 4, 8, 9, 10);

        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        List<LottoBall> lottoBalls2 = lottoNumbers2.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());
        lottoTickets.add(new LottoTicket(new LottoBalls(lottoBalls)));
        lottoTickets.add(new LottoTicket(new LottoBalls(lottoBalls2)));
    }

    @BeforeEach
    void initWinningLotto() {
        int[] winningNumber = {1, 2, 3, 4, 5, 6};
        int bonusNumber = 7;
        Set<Integer> winningNumbers = Arrays.stream(winningNumber)
                .boxed()
                .collect(Collectors.toSet());
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    @DisplayName("LottoTickets 정상 생성 테스트.")
    @Test
    void lottoTicketsGenerateTest() {
        Assertions.assertThatCode(() -> new LottoTickets(lottoTickets))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 결과 테스트")
    @Test
    void lottoTicketMatchesTest() {
        //given
        LottoTickets lottoTickets = new LottoTickets(this.lottoTickets);

        //when
        List<LottoRank> ranks = lottoTickets.findMatches(winningLotto);

        //then
        Assertions.assertThat(ranks.get(0)).isEqualTo(LottoRank.SIX_MATCHES);
        Assertions.assertThat(ranks.get(1)).isEqualTo(LottoRank.THREE_MATCHES);
    }
}