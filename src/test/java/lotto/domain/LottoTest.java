package lotto.domain;

import lotto.generator.ManualLottoGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {
    @Test
    void validateDistinctNumberTest_로또번호에_중복숫자가_있을_때() {
        List<LottoNumber> invalidNumbers = IntStream.rangeClosed(1, 5)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        invalidNumbers.add(new LottoNumber(5));

        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되지 않는 6개의 숫자여야 합니다.");
    }

    @Test
    void validateDistinctNumberTest_로또번호에_중복숫자가_없을_때() {
        List<LottoNumber> validNumbers = IntStream.rangeClosed(1, 6)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        new Lotto(validNumbers);
    }

    @ParameterizedTest
    @EmptySource
    void validateDistinctNumberTest_빈_문자를_입력했을_때(Set<LottoNumber> emptyValue) {
        assertThatThrownBy(() -> new Lotto(emptyValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되지 않는 6개의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_NULL을_입력했을_때(Set<LottoNumber> nullValue) {
        assertThatThrownBy(() -> new Lotto(nullValue))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("로또 번호가 입력되지 않았습니다.");
    }

    @Test
    void hasLottoNumber_해당_로또번호를_가지고_있을_때() {
        boolean expected = true;
        List<String> lottoNumbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        Lotto lotto = ManualLottoGenerator.createLotto(lottoNumbers);
        assertThat(lotto.hasLottoNumber(new LottoNumber(1))).isEqualTo(expected);
    }
}
