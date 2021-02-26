package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseInfoTest {

    @DisplayName("구매 정보 생성 테스트")
    @Test
    public void 구매_정보_생성_테스트() {
        PurchaseInfo purchaseInfo = new PurchaseInfo(new Money(2000), 2);

        assertThat(purchaseInfo.getPurchaseMoney()).isEqualTo(new Money(2000));
        assertThat(purchaseInfo.getPurchaseManualCount()).isEqualTo(2);
    }

    @DisplayName("예외 처리 : 음수, 1이상이 아닌 수동 구매 개수에 대한 예외")
    @ParameterizedTest
    @ValueSource(ints = {-5, 0})
    void 수동_구매_개수_예외(int testCount) {

        assertThatThrownBy(() -> new PurchaseInfo(new Money(2000), testCount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외 처리 : 구매금액 단위가 맞지 않는 예외")
    @ParameterizedTest
    @ValueSource(ints = {5500, 4900, 1234, 2500})
    void 금액단위_안맞으면_예외(int purchaseMoney) {
        assertThatThrownBy(() -> new PurchaseInfo(new Money(purchaseMoney), 2))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외 처리 : 구매금액이 음수인 예외")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -2000, -50000, -150000})
    void 금액이_음수이면_예외(int purchaseMoney) {
        assertThatThrownBy(() -> new PurchaseInfo(new Money(purchaseMoney), 2))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외 처리 : 수동 구매 횟수가 전체 횟수를 초과하는 경우")
    @ParameterizedTest
    @CsvSource({"2000,3", "10000,11"})
    void 수동_구매_횟수가_전체횟수_초과(int purchaseMoney, int purchaseManualCount) {
        assertThatThrownBy(() -> new PurchaseInfo(new Money(purchaseMoney), purchaseManualCount))
            .isInstanceOf(IllegalArgumentException.class);
    }

}