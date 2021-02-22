package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.view.InputView.convertToInts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ManualLottoGeneratorTest {
    private static ManualLottoGenerator manualLottoGenerator;

    //TODO:
    // test fixture
    public static Lotto createCustomLotto(String customNumbers) {
        int[] numbers = convertToInts(customNumbers.split(", "));
        return new ManualLottoGenerator(convertToNumbersSequence(numbers)).createLotto();
    }

    @BeforeEach
    void setUp() {
        manualLottoGenerator = new ManualLottoGenerator(convertToNumbersSequence(new int[]{1, 2, 3, 4, 5, 6}));
    }

    //TODO
    // static 지우기
    private static List<int[]> convertToNumbersSequence(int[] winningNumbersInput) {
        List<int[]> numberSequence = new ArrayList<>();
        numberSequence.add(winningNumbersInput);
        return numberSequence;
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
}