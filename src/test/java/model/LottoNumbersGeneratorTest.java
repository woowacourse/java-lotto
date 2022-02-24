package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersGeneratorTest {

    @SuppressWarnings(value = "unchecked")
    static class LottoNumbersGeneratorStub extends LottoNumbersGenerator {

        private final Set<Integer>[] numbers = new Set[]{
            Set.of(1, 2, 3, 4, 5, 6),
            Set.of(7, 8, 9, 10, 11, 12),
            Set.of(13, 14, 15, 16, 17, 18)
        };
        private int index = 0;

        @Override
        public LottoNumbers createLottoNumbers() {
            Set<Integer> lottoNumbers = numbers[index++];
            Iterator<Integer> iterator = lottoNumbers.iterator();
            return new LottoNumbers(List.of(iterator.next(), iterator.next(), iterator.next(), iterator.next(),
                    iterator.next(), iterator.next()));
        }
    }


    @Test
    @DisplayName("입력한 수 만큼 로또 발급 테스트")
    void createLottoNumbersTest() {
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGeneratorStub();
        List<LottoNumbers> lottoNumbersList = lottoNumbersGenerator.generate(3);
        assertThat(lottoNumbersList).hasSize(3);
        assertThat(lottoNumbersList).contains(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumbers(List.of(7, 8, 9, 10, 11, 12)),
                new LottoNumbers(List.of(13, 14, 15, 16, 17, 18))
        );
    }
}
