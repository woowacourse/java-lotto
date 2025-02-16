package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @DisplayName("구매한 로또 목록이 요구 사항과 동일한 형태로 출력된다.")
    @Test
    void WalletToStringIsSatisfiedRequest() {
        var size = 5;

        Cashier cashier = new Cashier(LOTTO_PRICE * size);
        int numberOfLotto = cashier.getNumberOfLotto();
        Wallet wallet = new Wallet(numberOfLotto);

        String output = wallet.toString();
        String[] lines = output.split("\n");
        assertThat(lines).hasSize(size);
    }

    @DisplayName("구매한 로또 전체를 지난주 당첨 로또와 비교한다.")
    @Test
    void testMatchCountListHasCorrectSize() {
        var size = 5;
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));
        Wallet wallet = new Wallet(size);

        List<MatchCount> matchCounts = wallet.getMatchCountList(lotto, 7);

        assertThat(matchCounts).hasSize(size);
    }
}
