package lotto.domain.utils;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoGeneratorTest {

    Lotto lotto;

    @Before
    public void setUp() {
        lotto = AutoLottoGenerator.makeLotto();
    }

    @Test
    public void auto_로또_번호_생성_확인() {
        int lottoNumberSize = lotto.getLotto().size();
        assertThat(lottoNumberSize).isEqualTo(6);
    }

    @Test
    public void auto_로또_번호_중복_유무_확인() {
        Set<Number> lottoSet = new HashSet<>(lotto.getLotto());
        assertThat(lottoSet.size()).isEqualTo(6);
    }
}
