package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    private List<Number> lottoNumbers;
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(Number.of(i));
        }

        lotto = new Lotto(lottoNumbers);
    }

    @Test
    void 중복이_있을때() {
        List<Number> lottoNumberDuplication = new ArrayList<>();
        lottoNumbers.add(Number.of(1));
        lottoNumbers.add(Number.of(1));

        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(lottoNumberDuplication);
        });
    }

    @Test
    void 로또번호의_갯수가_6개가_아닐때() {
        lottoNumbers.add(Number.of(7));

        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(lottoNumbers);
        });
    }

    @Test
    void 같은_수가_포함되어있는지_확인() {
        assertThat(lotto.isContains(Number.of(1))).isTrue();
    }

    @Test
    void 인덱스에_맞는_객체() {
        assertThat(lotto.getLottoByIndex(0)).isEqualTo(Number.of(1));
    }
}
