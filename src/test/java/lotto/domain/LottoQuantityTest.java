package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoQuantityTest {

    @Test
    @DisplayName("수동, 자동 로또의 가격 반환 테스트")
    void testLottoQuantity() {
        LottoQuantity lottoQuantity = new LottoQuantity(new Money("3000"), "2");
        assertThat(lottoQuantity.calculateFixedLottoPrice()).isEqualTo(new Money("2000"));
        assertThat(lottoQuantity.calculateAutoLottoPrice()).isEqualTo(new Money("1000"));
    }

    @Test
    @DisplayName("빈 값이 들어왔을 때 예외 테스트")
    void testValidateEmpty() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new LottoQuantity(new Money("3000"), "");
        }).withMessage("빈 값은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값이 들어왔을 때 예외 테스트")
    void testValidateNumber() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new LottoQuantity(new Money("3000"), "a");
        }).withMessage("숫자만 입력 가능합니다.");
    }

    @Test
    @DisplayName("최대 금액 초과하는 수동 로또 생성 시 예외 테스트")
    void testValidateFixedLottoQuantity() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new LottoQuantity(new Money("3000"), "4");
        }).withMessage("수동 로또의 개수는 구입 금액을 초과할 수 없습니다.");
    }
}
