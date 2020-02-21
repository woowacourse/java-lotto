package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {
    final int MIN_LOTTO_NUMBER = 1;
    final int MAX_LOTTO_NUMBER = 45;
    final int LOTTO_NUMBER_SIZE = 6;

    @Test
    void validateDistinctNumberTest_로또번호에_중복숫자가_있을_때() {
        Set<LottoNumber> invalidNumbers = new HashSet<>();
        for (int i = MIN_LOTTO_NUMBER; i <= 5; i++) {
            invalidNumbers.add(new LottoNumber(i));
        }
        invalidNumbers.add(new LottoNumber(5));

        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되지 않는 6개의 숫자여야 합니다.");
    }

    @Test
    void validateDistinctNumberTest_로또번호에_중복숫자가_없을_때() {
        Set<LottoNumber> invalidNumbers = new HashSet<>();
        for (int i = MIN_LOTTO_NUMBER; i <= 5; i++) {
            invalidNumbers.add(new LottoNumber(i));
        }
        invalidNumbers.add(new LottoNumber(MAX_LOTTO_NUMBER));
        new Lotto(invalidNumbers);
    }

    @Test
    void compare_두_로또_사이에_6개_로또_번호가_일치할_때() {
        int expected = 6;
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (int i = MIN_LOTTO_NUMBER; i < MIN_LOTTO_NUMBER + LOTTO_NUMBER_SIZE; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Lotto lotto = new Lotto(lottoNumbers);
        Lotto compareLotto = new Lotto(lottoNumbers);
        assertThat(lotto.compare(compareLotto) == expected);
    }

    @Test
    void compare_두_로또_사이에_일치하는_로또번호가_없을_때() {
        int expected = 0;
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (int i = MIN_LOTTO_NUMBER; i < MIN_LOTTO_NUMBER + LOTTO_NUMBER_SIZE; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }

        int startNumber = 10;
        Set<LottoNumber> compareLottoNumbers = new HashSet<>();
        for (int i = startNumber; i < startNumber + LOTTO_NUMBER_SIZE; i++) {
            compareLottoNumbers.add(new LottoNumber(i));
        }

        Lotto lotto = new Lotto(lottoNumbers);
        Lotto compareLotto = new Lotto(compareLottoNumbers);
        assertThat(lotto.compare(compareLotto) == expected);
    }
}
