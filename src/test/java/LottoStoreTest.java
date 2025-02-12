import static org.assertj.core.api.Assertions.*;

import model.LottoNumberGenerator;
import model.LottoStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStoreTest {

    private LottoStore lottoStore = new LottoStore(new LottoNumberGenerator());

    @DisplayName("구입 금액이 1000원 단위가 아나라면 예외를 발생시킨다.")
    @Test
    void 구입_금액이_1000원_단위가_아나라면_예외를_발생시킨다() {
        int purchasePrice = 1001;
        assertThatThrownBy(
                () -> lottoStore.buy(purchasePrice)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액에 해당하는 개수만큼 로또 티켓이 생성된다.")
    @Test
    void 구입_금액에_해당하는_개수만큼_로또_티켓이_생성된다() {
        int purchasePrice = 14000;
        assertThat(lottoStore.buy(purchasePrice).size()).isEqualTo(14);
    }
}
