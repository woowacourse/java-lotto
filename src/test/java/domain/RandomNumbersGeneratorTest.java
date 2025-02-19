package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumbersGeneratorTest {

    @DisplayName("랜덤 번호 6개를 성공적으로 생성한다")
    @Test
    void generateTest() {
        final List<Integer> numbers = new RandomNumbersGenerator().generate();
        assertThat(numbers).size().isEqualTo(6);
    }
}
