package lotto.domain;

import lotto.generator.LottoSelectedGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {
    @Test
    void validateDistinctNumberTest_로또번호에_중복숫자가_있을_때() {
        Set<LottoNumber> invalidNumbers = new HashSet<>();
        for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= 5; i++) {
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
        for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= 5; i++) {
            invalidNumbers.add(new LottoNumber(i));
        }
        invalidNumbers.add(new LottoNumber(LottoNumber.MAX_LOTTO_NUMBER));
        new Lotto(invalidNumbers);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validateEmpty_로또번호_빈_문자_또는_NULL(Set<LottoNumber> emptyValue) {
        assertThatThrownBy(() -> new Lotto(emptyValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호가 입력되지 않았습니다.");
    }

    @Test
    void hasLottoNumber_해당_로또번호를_가지고_있을_때() {
        boolean expected = true;
        Lotto lotto = new LottoSelectedGenerator("1, 2, 3, 4, 5, 6").create();
        assertThat(lotto.hasLottoNumber(new LottoNumber(1))).isTrue();
    }
}
