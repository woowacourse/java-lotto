package lotto.domain.purchase_info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseInfoTest {

    @Test
    @DisplayName("구매 정보는 수동 구매에 대한 입력과 자동 숫자로 생성")
    void createPurchaseInfo() {
        List<String> inputs = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7");
        int autoCount = 5;
        PurchaseInfo purchaseInfo = new PurchaseInfo(inputs, autoCount);

        assertThat(purchaseInfo.getManualNumbersInputs()).size().isEqualTo(2);
        assertThat(purchaseInfo.getAutoCount()).isEqualTo(5);
    }
}