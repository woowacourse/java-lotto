import domain.RandomGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RandomGeneratorTest {
    @Test
    void 난수_값_6개_생성_테스트() {
        //when & then
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);
        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    void 값들의_범위_테스트() {
        //when & then
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);

        for (int number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @Test
    void 값들의_중복되지_않아야한다() {
        //when & then
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);
        assertThat(numbers.size()).isEqualTo(numbers.stream().distinct().count());
    }

    @Test
    void 시드값을_통해_고정된_랜덤값을_확인할_수_있다() {

        // given
        Random random1 = new Random(1);
        Random random2 = new Random(1);
        // when
        int firstValue1 = random1.nextInt();
        int firstValue2 = random2.nextInt();
        //then
        assertEquals(firstValue1, firstValue2, "시드 값이 같으면 동일한 랜덤 값을 생성해야 합니다.");
    }
}
