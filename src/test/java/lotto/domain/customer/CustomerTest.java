package lotto.domain.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerTest {

    @DisplayName("예외 케이스 테스트: 요청 받은 수동 로또 수와 manualNumbers 갯수가 다른 경우 Exception")
    @Test
    void name1() {
        PurchaseInfo purchaseInfo = new PurchaseInfo(2000, 2);
        assertThatThrownBy(() -> new Customer(purchaseInfo, Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("입력받은 수동 로또 번호 수(%d개) 구매하려는 수(%d개)와 일치하지 않습니다", 1, 2));
    }
}
