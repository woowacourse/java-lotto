package utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class RandomNumberTest {

    @DisplayName("랜덤 번호는 1~45 사이의 숫자만 생성되어야 한다.")
    @RepeatedTest(100)
    void 랜덤_번호_생성_테스트() {
        List<Integer> randomNumbers = RandomNumber.generateNumbers(1);
        assertThat(randomNumbers.get(0)).isBetween(1, 45);
    }

    @DisplayName("로또 번호는 입력한 size만큼 생성되어야 한다.")
    @Test
    void 랜덤_번호_사이즈_테스트() {
        List<Integer> randomNumbers1 = RandomNumber.generateNumbers(10);
        List<Integer> randomNumbers2 = RandomNumber.generateNumbers(9);
        List<Integer> randomNumbers3 = RandomNumber.generateNumbers(8);

        assertThat(randomNumbers1.size()).isEqualTo(10);
        assertThat(randomNumbers2.size()).isEqualTo(9);
        assertThat(randomNumbers3.size()).isEqualTo(8);
    }

}
