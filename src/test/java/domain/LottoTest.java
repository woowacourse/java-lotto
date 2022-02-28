package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("정상적인 당첨 번호에 대한 생성 테스트")
    public void createValidLottoNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(6, 1, 2, 3, 4, 5);
        Lotto lotto = new Lotto(lottoNumber);
        assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @Test
    @DisplayName("45를 초과하는 당첨 번호에 대한 예외 테스트")
    public void createMaximumLimitExceedNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(46, 1, 2, 3, 4, 5);
        assertThatThrownBy(() -> {
            new Lotto(lottoNumber);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("46");
    }

    @Test
    @DisplayName("최소 허용 당첨 번호보다 작은 당첨 번호에 대한 예외 테스트")
    public void createMinimumLimitExceedNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(-1, 0, 2, 3, 4, 5);
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(lottoNumber);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("-1");
    }

    @Test
    @DisplayName("중복되는 당첨 번호 예외 테스트")
    public void createDuplicatedNumberTest() {
        List<Integer> lottoNumber = Arrays.asList(2, 2, 2, 3, 4, 5);
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(lottoNumber);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2");
    }

    @Test
    @DisplayName("당첨 번호와 비교하여 맞는 개수 테스트")
    void countSameNumbersTest() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> targetNumbers = Arrays.asList(2, 3, 4, 5, 7, 9);
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.countSameNumbers(targetNumbers)).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스 번호 포함 확인 테스트")
    void checkBonusInLottoNumbersTest() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        int bonusNumber = 6;
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(true);
    }

    @Test
    @DisplayName("보너스 번호 미포함 확인 테스트")
    void checkBonusNotInLottoNumbersTest() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        int bonusNumber = 10;
        assertThat(lotto.checkBonus(bonusNumber)).isEqualTo(false);
    }
}
