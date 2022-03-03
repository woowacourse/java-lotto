package lotto.model.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoStorageTest {

    @ParameterizedTest
    @ValueSource(ints = {14000, 1000, 5000})
    @DisplayName("로또 생성 개수 검증")
    void compareLottoWithWinningNumber(long money) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(List.of("1", "2", "3", "4", "5", "6")));
        LottoStorage lottoStorage = new LottoStorage(new LottoCount(money, 0), lottos);
        assertThat(lottoStorage.getLottoStorage().size()).isEqualTo((money / 1000 + 1));
    }
}
