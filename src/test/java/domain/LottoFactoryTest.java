package domain;

import domain.Lotto.Lotto;
import domain.Lotto.LottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {

    @Test
    @DisplayName("LottoFacotry의 generateAutoLotto를 이용해 자동 생성된 로또의 길이가 6인지 확인한다.")
    void generateAutoLotto() {
        int autoLottoCount = 1;
        Lotto actual = LottoFactory.generateAutoLottos(autoLottoCount).get(0);
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("LottoFactory의 generateManualLotto를 이용해 수동 생성된 로또의 길이가 6인지 확인한다.")
    void generateWinningLotto() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto actual = LottoFactory.generateManualLotto(lottoNumbers);
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("LottoFactory의 generateAutoLottos를 이용해 자동 생성된 로또 갯수가 일치하는지 확인한다.")
    void generateAutoLottos() {
        int autoLottoCount = 3;
        List<Lotto> autoLottos = LottoFactory.generateAutoLottos(autoLottoCount);
        int expected = 3;
        assertThat(autoLottos.size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("LottoFactory의 generateManualLottos를 이용해 수동 생성된 로또 갯수가 일치하는지 확인한다.")
    void generateManualLottos() {
        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            manualNumbers.add("1,2,3,4,5,6");
        }
        List<Lotto> autoLottos = LottoFactory.generateManualLottos(manualNumbers);
        int expected = 3;
        assertThat(autoLottos.size()).isEqualTo(expected);
    }
}