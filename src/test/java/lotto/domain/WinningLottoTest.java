package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    private Lotto lotto;

    @BeforeEach
    public void setUp() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    public void 올바른_번호가_입력됐을_때() {
        LottoNumber bonusBall = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

        assertThat(winningLotto).isExactlyInstanceOf(WinningLotto.class);
    }

    @Test
    public void 로또와_보너스볼이_중복됐을_때() {
        LottoNumber bonusBall = new LottoNumber(1);

        assertThatThrownBy(() -> {
            new WinningLotto(lotto, bonusBall);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
