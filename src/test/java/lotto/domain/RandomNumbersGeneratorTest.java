package lotto.domain;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumbersGeneratorTest {

    @DisplayName("정해진 로또 번호 개수대로 생성된다")
    @Test
    void testGeneratedCount() {
        RandomNumbersGenerator numberGenerator =
                new RandomNumbersGenerator(LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM, LOTTO_SIZE);
        List<Integer> lottoNumbers = numberGenerator.generate();

        assertThat(lottoNumbers.size()).isEqualTo(LOTTO_SIZE);
    }

    @DisplayName("로또 번호는 정해진 범위 내에서 생성된다.")
    @Test
    void testGeneratedNumbersRange() {
        NumbersGenerator generator = new RandomNumbersGenerator(LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM, LOTTO_SIZE);
        List<Integer> numbers = generator.generate();

        boolean inRange = numbers.stream().allMatch(n -> n >= 1 && n <= 45);
        assertThat(inRange).isTrue();
    }

    @DisplayName("로또 번호는 중복되지 않게 생성된다.")
    @Test
    void 로또_번호는_중복되지_않는다() {
        NumbersGenerator generator = new RandomNumbersGenerator(LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM, LOTTO_SIZE);
        List<Integer> numbers = generator.generate();

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        assertThat(numbers).hasSize(uniqueNumbers.size());
    }

}