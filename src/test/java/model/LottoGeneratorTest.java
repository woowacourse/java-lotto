package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.generator.LottosGenerator;
import model.generator.ManualGenerator;
import model.generator.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    private LottosGenerator randomGenerator;
    private LottosGenerator manualGenerator;

    @BeforeEach
    void setUp() {
        List<Lotto> manualLotto = List.of(Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 4, 5, 6))));
        randomGenerator = new RandomGenerator(3);
        manualGenerator = new ManualGenerator(manualLotto);
    }

    @Test
    void generateRandom() {
        assertThat(randomGenerator.createLottos()).hasSize(3);
    }

    @Test
    void generateManual() {
        assertThat(manualGenerator.createLottos()).contains(Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 4, 5, 6))));
    }
}