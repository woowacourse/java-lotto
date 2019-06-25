package domain;

import domain.lottonumber.RandomLottoNumberGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomLottoNumberGeneratorTest {
    @Test
    void 입력받은_개수만큼_LottoNumber를_뽑아주는지_테스트() {
        assertThat(RandomLottoNumberGenerator.generateNumbersAsManyAs(6).size()).isEqualTo(6);
    }

    @Test
    void 입력받은_개수가_로또번호_개수보다_크면_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class,
                () -> RandomLottoNumberGenerator.generateNumbersAsManyAs(46));
    }
}
