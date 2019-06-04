package lotto.domain;

import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidLottoCountException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumbersTest {
    @Test
    void 중복된_로또_번호가_들어온_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6));

        assertThrows(DuplicateLottoNumberException.class, () -> new LottoNumbers(numbers));
    }

    @Test
    void 다섯개의_번호가_입력된_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5));

        assertThrows(InvalidLottoCountException.class, () -> new LottoNumbers(numbers));
    }

    @Test
    void 일곱개의_번호가_입력된_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6),
                LottoNumber.valueOf(7));

        assertThrows(InvalidLottoCountException.class, () -> new LottoNumbers(numbers));
    }

    @Test
    void 숫자_4개가_겹치는_경우() {
        List<LottoNumber> numbers1 = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6));
        List<LottoNumber> numbers2 = Arrays.asList(
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6),
                LottoNumber.valueOf(7),
                LottoNumber.valueOf(8));

        assertThat(new LottoNumbers(numbers1).hasLottoNumber(new LottoNumbers(numbers2))).isEqualTo(4);
    }

    @Test
    void 해당_숫자가_있는_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6));

        assertThat(new LottoNumbers(numbers).hasLottoNumber(LottoNumber.valueOf(1))).isTrue();
    }

    @Test
    void 해당_숫자가_없는_경우() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6));

        assertThat(new LottoNumbers(numbers).hasLottoNumber(LottoNumber.valueOf(7))).isFalse();
    }
}
