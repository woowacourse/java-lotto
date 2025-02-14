package lotto.model.lotto.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class DefaultNumberGeneratorTest {

    private final DefaultNumberGenerator defaultNumberGenerator = new DefaultNumberGenerator();

    @Test
    void 번호가_min부터_max까지_size만큼_생성() {

        int max = 100;
        int min = 1;
        int size = 6;

        for (int i = 0; i < 1000; i++) {
            List<Integer> generated = defaultNumberGenerator.generate(min, max, size);
            int minGenerated = generated.stream().min(Integer::compareTo).get();
            int maxGenerated = generated.stream().max(Integer::compareTo).get();

            assertThat(minGenerated).isGreaterThanOrEqualTo(min);
            assertThat(maxGenerated).isLessThanOrEqualTo(max);
            assertThat(generated).hasSize(size);
        }
    }
}