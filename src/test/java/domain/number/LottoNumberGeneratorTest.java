package domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoNumberGeneratorTest {
    class OrderLottoNumberGenerator implements LottoNumberGenerator {

        @Override
        public List<Integer> createLottoNumber() {
            return Arrays.asList(1, 2, 3, 4, 5, 6);
        }
    }

    @DisplayName("createLottoNumber 정상 동작 테스트")
    @Test
    void CreateLottoNumberTest() {
        //Given
        final LottoNumberGenerator lottoNumberGenerator = new OrderLottoNumberGenerator();
        //When
        final List<Integer> lottoNumber = lottoNumberGenerator.createLottoNumber();
        //Then
        assertThat(lottoNumber).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}
