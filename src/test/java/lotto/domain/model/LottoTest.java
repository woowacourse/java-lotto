package lotto.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    List<Number> lottoNumbers = new ArrayList<>();
    Lotto lotto;

    @Before
    public void setUp() {
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(NumberSet.of(i));
        }
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    public void Lotto_생성_확인() {
        assertThat(lotto).isEqualTo(new Lotto(lottoNumbers));
    }

    @Test
    public void Lotto_중복_확인() {
        boolean isDuplicated = lotto.isContained(NumberSet.of(6));
        assertThat(isDuplicated).isEqualTo(true);
    }

    @Test
    public void Lotto_미중복_확인() {
        boolean isDuplicated = lotto.isContained(NumberSet.of(7));
        assertThat(isDuplicated).isEqualTo(false);
    }
}
