package lotterymachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LotteryTicketTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:0", "1,2,3,4,7,8:2", "1,2,3,7,8,9:3"}, delimiter = ':')
    @DisplayName("당첨번호와 일치하는 로또 숫자의 개수를 반환한다.")
    void countMatchingNumbers(String winningNumbers, int expected) {
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());
        LotteryTicket lotteryTicket = new LotteryTicket(input);

        List<LotteryNumber> inputWinningNumbers = Arrays.stream(winningNumbers.split(","))
                .map(i -> new LotteryNumber(Integer.parseInt(i)))
                .collect(Collectors.toList());

        assertThat(lotteryTicket.countMatchingNumbers(inputWinningNumbers)).isEqualTo(expected);
    }


    @ParameterizedTest
    @CsvSource(value = {"1:false", "7:true"}, delimiter = ':')
    @DisplayName("보너스 번호 보유 여부를 확인한다.")
    void matchBonusNumber(int bonusNumber, boolean expected) {
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());
        LotteryTicket lotteryTicket = new LotteryTicket(input);

        assertThat(lotteryTicket.containsNumber(new LotteryNumber(bonusNumber))).isEqualTo(expected);
    }
}
