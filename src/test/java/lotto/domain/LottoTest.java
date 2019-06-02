package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void 중복이_있을때() {
        List<Number> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new Number(1));
        lottoNumbers.add(new Number(2));
        lottoNumbers.add(new Number(2));

        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(lottoNumbers);
        });
    }

    @Test
    void 로또번호의_갯수가_6개가_아닐때() {
        List<Number> lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            lottoNumbers.add(new Number(i));
        }

        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(lottoNumbers);
        });
    }

    @Test
    void 같은_수가_포함되어있는지_확인() {
        List<Number> lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new Number(i));
        }

        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto.isContains(new Number(1))).isTrue();
    }
}
