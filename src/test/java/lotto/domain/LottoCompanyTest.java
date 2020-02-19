package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCompanyTest {

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
