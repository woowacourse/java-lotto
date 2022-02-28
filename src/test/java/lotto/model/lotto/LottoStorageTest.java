package lotto.model.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoStorageTest {

    @ParameterizedTest
    @ValueSource(strings = {"14000", "1000", "5000"})
    @DisplayName("로또 생성 개수 검증")
    void compareLottoWithWinningNumber(String money) {
        LottoStorage lottoStorage = new LottoStorage(new LottoCount(money));
        assertThat(lottoStorage.getLottoStorage().size()).isEqualTo(Integer.parseInt(money) / 1000);
    }
}