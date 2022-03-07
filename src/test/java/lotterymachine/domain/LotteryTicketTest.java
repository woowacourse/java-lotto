package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LotteryTicketTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:0", "1,2,3,4,7,8:2", "1,2,3,7,8,9:3"}, delimiter = ':')
    @DisplayName("당첨번호와 일치하는 로또 숫자의 개수를 반환한다.")
    void countMatchingNumbers(String winningNumbers, int expected) {
        List<Integer> input = IntStream.range(7, 13)
                .boxed()
                .collect(Collectors.toList());
        LotteryTicket lotteryTicket = LotteryTicket.from(input);
        List<Integer> inputWinningNumbers = Arrays.stream(winningNumbers.split(","))
                .map(i-> Integer.valueOf(i.trim()))
                .collect(Collectors.toList());
        assertThat(lotteryTicket.countMatchingNumbers(LotteryTicket.from(inputWinningNumbers))).isEqualTo(expected);
    }


    @ParameterizedTest
    @CsvSource(value = {"1:false", "7:true"}, delimiter = ':')
    @DisplayName("보너스 번호 보유 여부를 확인한다.")
    void matchBonusNumber(int bonusNumber, boolean expected) {
        List<Integer> input = IntStream.range(7, 13)
                .boxed()
                .collect(Collectors.toList());
        LotteryTicket lotteryTicket = LotteryTicket.from(input);

        assertThat(lotteryTicket.containsNumber(LotteryNumber.from(bonusNumber))).isEqualTo(expected);
    }

    @Test
    @DisplayName("수동 로또 번호들을 입력 받아 LotteryTicket 리스트를 생성한다.")
    void createLotteryTicks() {
        List<List<Integer>> input = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6));
        List<LotteryTicket> value = LotteryTicket.createLotteryTickets(input);
        assertThat(value.size()).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("입력 받은 개수 만큼 자동 로또 번호를 생성하여 LotteryTicket 리스트를 만든다.")
    void createAutoLotteryTickets(int input) {
        List<LotteryNumber> lotteryNumbers = LotteryNumber.from(List.of(1, 2, 3, 4, 5, 6));
        List<LotteryTicket> value = LotteryTicket.createAutoLotteryTickets(input, () -> lotteryNumbers);
        assertThat(value.size()).isEqualTo(input);
    }

    @Test
    @DisplayName("로또 넘버 숫자가 6개 미만일 때, 에러가 발생한다.")
    void validateSize() {
        List<Integer> input = List.of(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> {
            LotteryTicket lotteryTicket = LotteryTicket.from(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자는 여섯개를 입력해야합니다.");
    }

    @Test
    @DisplayName("로또 넘버가 중복될 시, 에러가 발생한다.")
    void validateDuplication() {
        List<Integer> input = List.of(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> {
            LotteryTicket lotteryTicket = LotteryTicket.from(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 숫자를 입력 받았습니다.");
    }

    @Test
    @DisplayName("로또 넘버 조회 시, 정렬한 값을 반환한다.")
    void getNumbers() {
        List<Integer> lotteryNumbers = List.of(6, 5, 4, 3, 2, 1);
        LotteryTicket lotteryTicket = LotteryTicket.from(lotteryNumbers);
        List<Integer> numbers = lotteryTicket.getNumbers();
        assertThat(numbers.get(0)).isEqualTo(1);
    }
}