package lotto.domain;

import lotto.domain.result.win.WinningLotto;
import lotto.domain.ticket.LottoCompany;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCompanyTest {

    @DisplayName("우승 로또 만들기")
    @Test
    void makeWinningLotto() {
        //given
        Set<LottoBall> lottoBalls = aLottoBalls(1, 2, 3, 4, 5, 6);
        LottoBall bonusBall = LottoFactory.findLottoBallByNumber(7);
        WinningLotto expectedLotto = new WinningLotto(lottoBalls, bonusBall);

        //when
        WinningLotto winningLotto = LottoCompany.makeWinningLotto(7, 1, 2, 3, 4, 5, 6);

        //then
        assertThat(winningLotto).isEqualTo(expectedLotto);
    }

    private Set<LottoBall> aLottoBalls(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoFactory::findLottoBallByNumber)
                .collect(Collectors.toSet());
    }
}
