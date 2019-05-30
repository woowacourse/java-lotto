package lotto.domain.utils;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoGeneratorTest {

    List<Number> inputLottoNumbers = new ArrayList<>();
    Lotto lotto;

    @Before
    public void setUp() {
        for (int i = 1; i <= 6; i++ ) {
            inputLottoNumbers.add(NumberSet.of(i));
        }
        lotto = ManualLottoGenerator.makeLotto(inputLottoNumbers);
    }

    @Test
    public void auto_로또_번호_생성_확인() {
        int lottoNumberSize = lotto.getLotto().size();
        assertThat(lottoNumberSize).isEqualTo(6);
    }

    @Test
    public void auto_로또_번호_중복_유무_확인() {
        Set<Number> lottoNumberSet = new HashSet<>(lotto.getLotto());
        assertThat(lottoNumberSet.size()).isEqualTo(6);
    }
}
