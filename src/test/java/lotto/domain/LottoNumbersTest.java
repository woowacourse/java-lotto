package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumbersTest {
    @Test
    void 중복된_로또_번호가_들어온_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(1),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));

        assertThrows(IllegalArgumentException.class, () -> new LottoNumbers(numbers));
    }

    @Test
    void 다섯개의_번호가_입력된_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(1),
                new LottoNumber(3),
                new LottoNumber(3),
                new LottoNumber(4));

        assertThrows(IllegalArgumentException.class, () -> new LottoNumbers(numbers));
    }

    @Test
    void 일곱개의_번호가_입력된_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7));

        assertThrows(IllegalArgumentException.class, () -> new LottoNumbers(numbers));
    }

    @Test
    void 숫자_4개가_겹치는_경우() {
        List<LottoNumber> numbers1 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));
        List<LottoNumber> numbers2 = Arrays.asList(
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7),
                new LottoNumber(8));

        assertThat(new LottoNumbers(numbers1).match(new LottoNumbers(numbers2))).isEqualTo(4);
    }

    @Test
    void 해당_숫자가_있는_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));

        assertThat(new LottoNumbers(numbers).match(new LottoNumber(1))).isTrue();
    }

    @Test
    void 해당_숫자가_없는_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));

        assertThat(new LottoNumbers(numbers).match(new LottoNumber(7))).isFalse();
    }
}
