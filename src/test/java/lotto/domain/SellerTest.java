package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SellerTest {

    @Test
    @DisplayName("금액에 맞는 수의 로또를 만드는지 확인")
    void sell() {
        Seller seller = new Seller();
        int count = 3;
        List<Lotto> lottos = seller.sell(count);
        assertThat(lottos.size() == count);
    }
}
