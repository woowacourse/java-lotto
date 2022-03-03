package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.strategy.LottoGeneratorStrategy;
import domain.strategy.RandomLottoGeneratorStrategy;

class LottoNumbersGeneratorTest {

    @Test
    @DisplayName("랜덤방식의 로또를 생성하는 기능")
    void createRandomLotto() {
        LottoGeneratorStrategy lottoGeneratorStrategy = new RandomLottoGeneratorStrategy();

        Lotto lotto = lottoGeneratorStrategy.generate();

        assertThat(lotto).isNotNull();
    }
}
