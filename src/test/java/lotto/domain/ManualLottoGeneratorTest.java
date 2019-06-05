package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoGeneratorTest {
    @Test
    public void 로또를_잘_만드는지_확인() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Lotto lottoByMe = new Lotto(lottoNumbers);
        Lotto lottoByGenerator = ManualLottoGenerator.generate(Arrays.asList("1", "2", "3", "4", "5", "6"));

        assertThat(lottoByGenerator).isEqualTo(lottoByMe);
    }
}
