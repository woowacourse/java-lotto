package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class LottoNumbersTest {
    private SortedSet<LottoNumber> numbers;

    @BeforeEach
    void setUp() {
        numbers = new TreeSet<>(Arrays.asList(
                LottoNumberFactory.getInstance(1), LottoNumberFactory.getInstance(2), LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(4), LottoNumberFactory.getInstance(5), LottoNumberFactory.getInstance(6)));
    }

    @Test
    @DisplayName("생성 테스트")
    void test1() {
        Assertions.assertThatCode(() -> new LottoNumbers(numbers)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("null값 입력시 예외처리")
    void test2() {
        Assertions.assertThatThrownBy(() -> new LottoNumbers(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    @DisplayName("인자가 6개가 아니면 예외처리")
    void test3() {
        SortedSet<LottoNumber> lottoNumbers = new TreeSet<>();
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
        lottoNumbers.add(LottoNumberFactory.getInstance(1));
        lottoNumbers.add(LottoNumberFactory.getInstance(2));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
        lottoNumbers.add(LottoNumberFactory.getInstance(3));
        lottoNumbers.add(LottoNumberFactory.getInstance(4));
        lottoNumbers.add(LottoNumberFactory.getInstance(5));
        lottoNumbers.add(LottoNumberFactory.getInstance(6));
        lottoNumbers.add(LottoNumberFactory.getInstance(7));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
    }

    @Test
    @DisplayName("비교 테스트")
    void test4() {
        SortedSet<LottoNumber> numbers2 = new TreeSet<>(Arrays.asList(
                LottoNumberFactory.getInstance(1), LottoNumberFactory.getInstance(2), LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(9), LottoNumberFactory.getInstance(45), LottoNumberFactory.getInstance(8)));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        LottoNumbers lottoNumbers2 = new LottoNumbers(numbers);
        LottoNumbers lottoNumbers3 = new LottoNumbers(numbers2);
        Assertions.assertThat(lottoNumbers.calculateMatchNumber(lottoNumbers2)).isEqualTo(6);
        Assertions.assertThat(lottoNumbers.calculateMatchNumber(lottoNumbers3)).isEqualTo(3);
    }
}
