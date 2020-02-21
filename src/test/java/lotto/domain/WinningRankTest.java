package lotto.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class WinningRankTest {
    @ParameterizedTest(name = "{0}")
    @DisplayName("1등부터 꼴등까지 골라내는 테스트")
    @CsvSource(value = {
            "1등_테스트 1,2,3,4,5,6 FIRST_RANK",
            "2등_테스트 1,2,3,4,5,7 SECOND_RANK",
            "3등_테스트 1,2,3,4,5,10 THIRD_RANK",
            "4등_테스트 1,2,3,4,44,45 FOURTH_RANK",
            "5등_테스트 1,2,3,43,44,45 FIFTH_RANK",
            "미당첨_테스트 1,2,40,41,42,43 NO_RANK"}, delimiter = ' ')
    void select_rank_test( String header, String input, WinningRank winningRank) {
        String[] lottoTicketNumbers = input.split(",");
        int[] winningBallInputs = {1, 2, 3, 4, 5, 6};
        int bonus = 7;

        List<LottoBall> lottoTicket = Arrays.stream(lottoTicketNumbers)
                .map(lottoTicketNumber -> LottoBallFactory.findByLottoBall(Integer.parseInt(lottoTicketNumber)))
                .collect(Collectors.toList());
        List<LottoBall> winningBallValues = Arrays.stream(winningBallInputs)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toList());
        WinningBalls winningBalls = new WinningBalls(winningBallValues, LottoBallFactory.findByLottoBall(bonus));
        int correctCount = winningBalls.hitLottoBalls(new LottoTicket(lottoTicket));
        boolean correctBonusNumber = winningBalls.hitBonusBall(new LottoTicket(lottoTicket));
        Assertions.assertThat(WinningRank.selectRank(correctCount, correctBonusNumber)).isEqualTo(winningRank);
    }
}