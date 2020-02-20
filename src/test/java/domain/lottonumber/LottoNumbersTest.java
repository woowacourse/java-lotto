package domain.lottonumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoNumbersTest {
    @Test
    void 생성_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(1))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(2))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(3))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(4))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(5))).isTrue();
        Assertions.assertThat(lottoNumbers.contains(LottoNumber.of(6))).isTrue();
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
        lottoNumbers.add(LottoNumber.of(1));
        lottoNumbers.add(LottoNumber.of(2));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
        lottoNumbers.add(LottoNumber.of(3));
        lottoNumbers.add(LottoNumber.of(4));
        lottoNumbers.add(LottoNumber.of(5));
        lottoNumbers.add(LottoNumber.of(6));
        lottoNumbers.add(LottoNumber.of(7));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
    }

    @Test
    void 비교_테스트() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6));
        List<LottoNumber> numbers2 = Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(9), LottoNumber.of(45), LottoNumber.of(8));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        LottoNumbers lottoNumbers2 = new LottoNumbers(numbers);
        LottoNumbers lottoNumbers3 = new LottoNumbers(numbers2);

        Assertions.assertThat(lottoNumbers.calculateMatchNumber(lottoNumbers2)).isEqualTo(6);
        Assertions.assertThat(lottoNumbers.calculateMatchNumber(lottoNumbers3)).isEqualTo(3);
    }

    @Test
    void 중복이_있으면_예외_처리() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(1), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(1));
        Assertions.assertThatThrownBy(() -> new LottoNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 인자가 존재합니다.");
    }
}
