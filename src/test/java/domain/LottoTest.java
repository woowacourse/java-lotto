package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    public void createMaximumLimitExceedNumberTest() {
        assertThatThrownBy(() -> {
            LottoNumber.of(500);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createValidLottoNumberTest() {
        List<LottoNumber> lottoNumber = toLottoNumbers(Arrays.asList(6, 1, 2, 3, 4, 5));
        Lotto lotto = new PassiveLotto(lottoNumber);
        assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @Test
    public void createMinimumLimitExceedNumberTest() {
        assertThatThrownBy(() -> {
            LottoNumber.of(-5);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createDuplicatedNumberTest() {
        assertThatThrownBy(() -> {
            List<LottoNumber> lottoNumber = toLottoNumbers(Arrays.asList(2, 2, 2, 3, 4, 5));
            Lotto lotto = new PassiveLotto(lottoNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void countSameNumbersTest() {
        List<LottoNumber> lottoNumbers = toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<LottoNumber> targetNumbers = toLottoNumbers(Arrays.asList(2, 3, 4, 5, 7, 9));
        Lotto lotto = new PassiveLotto(lottoNumbers);
        assertThat(lotto.countSameNumbers(targetNumbers)).isEqualTo(4);
    }

    @Test
    void checkBonusInLottoNumbersTest() {
        List<LottoNumber> lottoNumbers = toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new PassiveLotto(lottoNumbers);
        LottoNumber bonusNumber = LottoNumber.of(6);
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(true);
    }

    @Test
    void checkBonusNotInLottoNumbersTest() {
        List<LottoNumber> lottoNumbers = toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new PassiveLotto(lottoNumbers);
        LottoNumber bonusNumber = LottoNumber.of(10);
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(false);
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> list) {
        return list.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }
}
