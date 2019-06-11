package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    public void setUp() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @Test
    public void 올바른_숫자가_입력되었을_때() {
        lottoNumbers.add(new LottoNumber(6));

        assertThat(new Lotto(lottoNumbers)).isExactlyInstanceOf(Lotto.class);
    }

    @Test
    public void 중복된_숫자가_입력되었을_때() {
        lottoNumbers.add(new LottoNumber(1));

        assertThatThrownBy(() -> {
            new Lotto(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 로또_숫자가_6개보다_적을_때() {
        assertThatThrownBy(() -> {
            new Lotto(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 로또_숫자가_6개보다_많을_때() {
        lottoNumbers.add(new LottoNumber(6));
        lottoNumbers.add(new LottoNumber(7));

        assertThatThrownBy(() -> {
            new Lotto(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 로또가_특정_숫자를_포함하는지_확인() {
        lottoNumbers.add(new LottoNumber(6));
        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto.contains(new LottoNumber(1))).isTrue();
    }

    @Test
    public void compareTo_테스트() {
        lottoNumbers.add(new LottoNumber(6));
        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto.compareTo(lotto)).isEqualTo(6);
    }
}
