package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLotteryTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 일치할 시, 에러를 발생시킨다.")
    void validateBonusNumber() {
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::of)
                .collect(Collectors.toList());
        LotteryNumber inputBonusNumber = LotteryNumber.of(7);

        assertThatThrownBy(() -> {
            WinningLottery winningLottery = new WinningLottery(input, inputBonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼이 당첨 번호와 중복됩니다.");
    }

    @Test
    @DisplayName("당첨 번호 개수가 6개가 아닐 시, 에러를 발생시킨다.")
    void validateSize() {
        List<LotteryNumber> input = IntStream.range(7, 12)
                .mapToObj(LotteryNumber::of)
                .collect(Collectors.toList());
        LotteryNumber inputBonusNumber = LotteryNumber.of(7);

        assertThatThrownBy(() -> {
            WinningLottery winningLottery = new WinningLottery(input, inputBonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호 숫자는 여섯개를 입력해야합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 서로 중복될 시, 에러를 발생시킨다.")
    void validateDuplication() {
        List<LotteryNumber> input = IntStream.range(7, 12)
                .mapToObj(LotteryNumber::of)
                .collect(Collectors.toList());
        input.add(LotteryNumber.of(7));
        LotteryNumber inputBonusNumber = LotteryNumber.of(7);

        assertThatThrownBy(() -> {
            WinningLottery winningLottery = new WinningLottery(input, inputBonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 숫자를 입력 받았습니다.");
    }

    @Test
    @DisplayName("LotteryTicket을 입력 받아, 해당하는 Rank를 조회한다.")
    void getWinningLotteryRank() {
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::of)
                .collect(Collectors.toList());
        LotteryNumber inputBonusNumber = LotteryNumber.of(14);
        WinningLottery winningLottery = new WinningLottery(input, inputBonusNumber);
        LotteryTicket lotteryTicket = new LotteryTicket(input);

        WinningLotteryRank winningLotteryRank = winningLottery.getWinningLotteryRank(lotteryTicket);

        assertThat(winningLotteryRank).isEqualTo(WinningLotteryRank.SIX);
    }
}