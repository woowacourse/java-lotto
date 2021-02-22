package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.view.InputView.convertStringsToInts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ManualLottoGeneratorTest {
    private static ManualLottoGenerator manualLottoGenerator;

    //TODO:
    // test fixture
    public static Lotto createCustomLotto(String customNumbers) {
        int[] numbers = convertStringsToInts(customNumbers.split(", "));
        return new ManualLottoGenerator(numbers).createLotto();
    }

    @BeforeEach
    void setUp() {
        manualLottoGenerator = new ManualLottoGenerator(new int[]{1, 2, 3, 4, 5, 6});
    }

    @DisplayName("주어진 값에 맞게 로또 객체를 생성해 내는지")
    @Test
    void createLotto() {
        assertThat(manualLottoGenerator.createLotto()).isEqualTo(
                new Lotto(Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6))));
    }

    @DisplayName("주어진 값에 맞게 로또 넘버 리스트를 만들어 내는지")
    @Test
    void createLottoNumbers() {
        assertThat(manualLottoGenerator.createLottoNumbers()).isEqualTo(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)));
    }
}