package lotto.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoGeneratorTest {

    List<Integer> inputNumber = new ArrayList<>();
    List<LottoNumber> manuallyGeneratedLottoNumbers = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 1; i <= 6; i++ ) {
            inputNumber.add(i);
        }
        manuallyGeneratedLottoNumbers = ManualLottoGenerator.makeLotto(inputNumber);
    }

    @Test
    public void auto_로또_번호_생성_확인() {
        int lottoNumberSize = manuallyGeneratedLottoNumbers.size();
        assertThat(lottoNumberSize).isEqualTo(6);
    }

    @Test
    public void auto_로또_번호_중복_유무_확인() {
        Set<LottoNumber> lottoNumberSet = new HashSet<>();
        for (LottoNumber lottoNumber : manuallyGeneratedLottoNumbers) {
            lottoNumberSet.add(lottoNumber);
        }
        assertThat(lottoNumberSet.size()).isEqualTo(6);
    }
}
