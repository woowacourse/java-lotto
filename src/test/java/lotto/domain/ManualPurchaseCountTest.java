package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualPurchaseCountTest {

    @DisplayName("수동 구매 로또 갯수 저장 확인")
    @Test
    void manualPurchaseCount(){
        ManualPurchaseCount manualPurchaseCount = new ManualPurchaseCount(3);

        assertThat(manualPurchaseCount.getManualPurchaseCount()).isEqualTo(3);
    }

    @DisplayName("0이하의 숫자를 입력하였을 경우 확인")
    @Test
    void minusInputCheck(){
        assertThatThrownBy(()->{
            new ManualPurchaseCount(-1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0이상의 숫자를 입력하세요. ");
    }
}
