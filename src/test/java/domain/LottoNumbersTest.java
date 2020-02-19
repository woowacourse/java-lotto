package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoNumbersTest {
    @Test
    void 생성_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(new LottoNumber(6))).isTrue();
    }

    @Test
    void null값_입력시_예외처리() {
        Assertions.assertThatThrownBy(() -> new LottoNumbers(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @Test
    void 인자가_6개가_아니면_예외처리() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
        lottoNumbers.add(new LottoNumber(3));
        lottoNumbers.add(new LottoNumber(4));
        lottoNumbers.add(new LottoNumber(5));
        lottoNumbers.add(new LottoNumber(6));
        lottoNumbers.add(new LottoNumber(7));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
    }

    @Test
    void 비교_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        List<LottoNumber> numbers2 = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(9), new LottoNumber(45), new LottoNumber(8));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        LottoNumbers lottoNumbers2 = new LottoNumbers(numbers);
        LottoNumbers lottoNumbers3 = new LottoNumbers(numbers2);

        Assertions.assertThat(lottoNumbers.calculateMatchNumber(lottoNumbers2)).isEqualTo(6);
        Assertions.assertThat(lottoNumbers.calculateMatchNumber(lottoNumbers3)).isEqualTo(3);
    }

    @Test
    void 중복이_있으면_예외_처리() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(1), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(1));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 인자가 존재합니다.");
    }
}
