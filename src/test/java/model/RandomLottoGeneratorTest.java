package model;

import static org.assertj.core.api.Assertions.assertThatCode;

import model.generator.LottoGenerator;
import model.generator.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomLottoGeneratorTest {

    @Test
    @DisplayName("무작위로 랜덤 로또번호를 1 ~ 45 사이의 중복없는 수로 가져오는 지 확인")
    void createRandomNumbers() {
        LottoGenerator generator = new RandomLottoGenerator();
        assertThatCode(() -> generator.createLotto())
                .doesNotThrowAnyException();
    }
}
