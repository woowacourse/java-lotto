package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    public void createMaximumLimitExceedNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(46, 1, 2, 3, 4, 5);
        assertThatThrownBy(() -> {
            new Lotto(lottoNumber);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("46");
    }

    @Test
    public void createValidLottoNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(6, 1, 2, 3, 4, 5);
        Lotto lotto = new Lotto(lottoNumber);
        assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @Test
    public void createMinimumLimitExceedNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(-1, 0, 2, 3, 4, 5);
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(lottoNumber);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("-1");
    }

    @Test
    public void createDuplicatedNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(2, 2, 2, 3, 4, 5);
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(lottoNumber);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2");
    }

    @Test
    void countSameNumbersTest() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> targetNumbers = Arrays.asList(2, 3, 4, 5, 7, 9);
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.countSameNumbers(targetNumbers)).isEqualTo(4);
    }

    @Test
    void checkBonusInLottoNumbersTest() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        int bonusNumber = 6;
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(true);
    }

    @Test
    void checkBonusNotInLottoNumbersTest() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        int bonusNumber = 10;
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(false);
    }
}
