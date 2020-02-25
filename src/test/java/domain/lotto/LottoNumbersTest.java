package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class LottoNumbersTest {
    @Test
    void 생성_테스트() {
        SortedSet<LottoNumber> numbers = new TreeSet<>(Arrays.asList(
                LottoNumberFactory.getInstance(1), LottoNumberFactory.getInstance(2), LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(4), LottoNumberFactory.getInstance(5), LottoNumberFactory.getInstance(6)));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumberFactory.getInstance(6))).isTrue();
    }

    @Test
    void null값_입력시_예외처리() {
        Assertions.assertThatThrownBy(() -> new LottoNumbers(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    void 인자가_6개가_아니면_예외처리() {
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
    void 비교_테스트() {
        SortedSet<LottoNumber> numbers = new TreeSet<>(Arrays.asList(
                LottoNumberFactory.getInstance(1), LottoNumberFactory.getInstance(2), LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(4), LottoNumberFactory.getInstance(5), LottoNumberFactory.getInstance(6)));
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
