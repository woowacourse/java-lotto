package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PurchaseTest {

    @Test
    void 구매_정상_생성_확인() {
        Purchase purchase = new Purchase(20000, 1);
        assertThat(purchase.getAutoCount())
            .isEqualTo(19);
    }

}