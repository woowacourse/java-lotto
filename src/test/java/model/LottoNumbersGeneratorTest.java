package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersGeneratorTest {

    static class NumberQueueStub implements NumberQueue {

        private final int[] numbers = {
            1, 2, 3, 4, 5, 6,
            7, 8, 9, 10, 11, 12,
            13, 14, 15, 16, 17, 18
        };
        private int index = 0;

        @Override
        public boolean hasNext() {
            return numbers.length > index;
        }

        @Override
        public int get() {
            return numbers[index++];
        }
    }


    @Test
    @DisplayName("입력한 수 만큼 로또 발급 테스트")
    void createLottoNumbersTest() {
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator(
            new NumberQueueStub());
        List<LottoNumbers> lottoNumbersList = lottoNumbersGenerator.generate(3);
        assertThat(lottoNumbersList).hasSize(3);
        assertThat(lottoNumbersList).contains(
                LottoNumbers.withSixNumbers(1,2,3,4,5,6),
                LottoNumbers.withSixNumbers(7,8,9,10,11,12),
                LottoNumbers.withSixNumbers(13,14,15,16,17,18)
        );

    }
}
