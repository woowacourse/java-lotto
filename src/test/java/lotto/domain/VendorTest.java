package lotto.domain;

import lotto.constant.WinningTier;
import lotto.utility.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class VendorTest {

    static class TestLottoNumberGenerator extends LottoNumberGenerator {
        @Override
        public List<Integer> generateNumbers(int maxNumber, int count) {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @DisplayName("입력된 구매 금액이 1000단위가 아닌 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1_500})
    void 입력된_구매_금액이_1000단위가_아닌_경우_예외_발생(int purchaseAmount) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Vendor(new LottoNumberGenerator(), purchaseAmount));
    }

    @DisplayName("구매한 개수만큼의 로또를 생성할 수 있다.")
    @Test
    void 구매한_개수만큼의_로또를_생성할_수_있다() {
        final int purchaseAmount = 10_000;
        int expectedCount = 10;
        Vendor vendor = new Vendor(new LottoNumberGenerator(), purchaseAmount);
        Lottos lottos = vendor.issueLottos();

        assertThat(lottos.getLottoCount()).isEqualTo(expectedCount);
    }

    @DisplayName("로또 구매 시 무작위의 번호를 가진 로또 객체를 생성할 수 있다.")
    @Test
    void 로또_구매_시_무작위의_번호를_가진_로또_객체를_생성할_수_있다() {
        final int purchaseAmount = 1_000;
        Lotto expectedInstance = new Lotto(1, 2, 3, 4, 5, 6);
        Vendor vendor = new Vendor(new TestLottoNumberGenerator(), purchaseAmount);
        Lottos lottos = vendor.issueLottos();

        assertThat(lottos.getLottos().getFirst()).isEqualTo(expectedInstance);
    }

    @DisplayName("수익률을 올바르게 계산할 수 있다.")
    @Test
    void 수익률을_올바르게_계산할_수_있다() {
        final int purchaseAmount = 10_000;
        Vendor vendor = new Vendor(new LottoNumberGenerator(), purchaseAmount);
        List<WinningTier> winningTiers = List.of(WinningTier.FOURTH, WinningTier.FIFTH, WinningTier.EMPTY);
        double expectedProfit = (double) 55_000 / purchaseAmount;

        assertThat(vendor.calculateProfit(winningTiers)).isEqualTo(expectedProfit);
    }
}
