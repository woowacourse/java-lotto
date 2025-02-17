package lotto.domain;

import lotto.constant.WinningTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class VendorTest {

    private final int PURCHASE_AMOUNT = 10000;
    private Vendor vendor;

    @BeforeEach
    void beforeEach() {
        vendor = new Vendor(this.PURCHASE_AMOUNT);
    }

    @DisplayName("입력된 구매 금액이 1000단위가 아닌 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1500})
    void 입력된_구매_금액이_1000단위가_아닌_경우_예외_발생(int purchaseAmount) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Vendor(purchaseAmount));
    }

    @DisplayName("구매 금액에 해당하는 로또 개수를 계산할 수 있다.")
    @Test
    void 구매_금액에_해당하는_로또_개수를_계산할_수_있다() {
        int expectedValue = 10;

        assertThat(vendor.calculateLottoCount()).isEqualTo(expectedValue);
    }

    @DisplayName("구매한 개수만큼의 로또를 생성할 수 있다.")
    @Test
    void 구매한_개수만큼의_로또를_생성할_수_있다() {
        int expectedCount = 10;
        List<Lotto> lottos = vendor.issueLottos();

        assertThat(lottos.size()).isEqualTo(expectedCount);
    }

    @DisplayName("수익률을 올바르게 계산할 수 있다.")
    @Test
    void 수익률을_올바르게_계산할_수_있다() {
        List<WinningTier> winningTiers = List.of(WinningTier.FOURTH, WinningTier.FIFTH, WinningTier.EMPTY);
        double expectedProfit = (double) 55000 / this.PURCHASE_AMOUNT;

        assertThat(vendor.calculateProfit(winningTiers)).isEqualTo(expectedProfit);
    }
}
