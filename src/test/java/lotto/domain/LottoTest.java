package lotto.domain;

import lotto.domain.utils.ManualLottoGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    List<Integer> inputNumbers = new ArrayList<>();
    List<Number> lottoNumbers;
    Lotto lotto;

    @Before
    public void setUp() {
        for (int i = 1; i <= 6; i++) {
            inputNumbers.add(i);
        }
        lottoNumbers = ManualLottoGenerator.makeLotto(inputNumbers);
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    public void Lotto_생성_확인() {
        assertThat(lotto).isEqualTo(new Lotto(lottoNumbers));
    }

    @Test
    public void Lotto_중복_확인() {
        boolean isDuplicated = lotto.isContained(new Number(6));
        assertThat(isDuplicated).isEqualTo(true);
    }

    @Test
    public void Lotto_미중복_확인() {
        boolean isDuplicated = lotto.isContained(new Number(7));
        assertThat(isDuplicated).isEqualTo(false);
    }
}
