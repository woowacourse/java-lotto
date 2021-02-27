package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SellerTest {

    @Test
    @DisplayName("금액에 맞는 수의 로또를 만드는지 확인")
    void sell() {
        Seller seller = new Seller();
        int count = 3;
        LottoGroup lottos = seller.sellAuto(count);
        assertThat(lottos.getLottoGroup().size() == count).isTrue();
    }
}
