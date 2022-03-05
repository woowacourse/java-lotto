package lotterymachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1~45 범위에 벗어난 숫자를 입력 시, 에러 발생")
    void validateNumber(int input) {
        Assertions.assertThatThrownBy(() -> {
                    LotteryNumber lotteryNumber = LotteryNumber.from(input);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이의 값이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 45})
    @DisplayName("1~45 범위의 숫자를 입력 받아 캐싱된 객체를 가져온다.")
    void create(int input) {
        LotteryNumber lotteryNumber = LotteryNumber.from(input);
        assertThat(lotteryNumber.getNumber()).isEqualTo(input);
    }

    @Test
    @DisplayName("숫자 리스트를 입력 받아 캐싱된 객체 리스트를 가져온다.")
    void createLotteryNumbers() {
        List<Integer> input = IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(Collectors.toList());
        List<LotteryNumber> value = LotteryNumber.from(input);
        assertThat(value.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 숫자 리스트를 숫자 크기에 따라 정렬한다.")
    void comparable() {
        List<LotteryNumber> numbers = Arrays.asList(LotteryNumber.from(10), LotteryNumber.from(1));
        Collections.sort(numbers);
        assertThat(numbers.get(0).getNumber()).isEqualTo(1);
    }
}