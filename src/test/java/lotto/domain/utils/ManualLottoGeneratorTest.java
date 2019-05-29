package lotto.domain.utils;

import lotto.domain.Number;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoGeneratorTest {

    List<Integer> inputNumber = new ArrayList<>();
    List<Number> manuallyGeneratedLottoNumbers = new ArrayList<>();

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
        Set<Number> lottoNumberSet = new HashSet<>();
        for (Number lottoNumber : manuallyGeneratedLottoNumbers) {
            lottoNumberSet.add(lottoNumber);
        }
        assertThat(lottoNumberSet.size()).isEqualTo(6);
    }
}
