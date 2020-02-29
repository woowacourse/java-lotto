package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStoreTest {
	@Test
	@DisplayName("로또 수동 + 자동 구매 테스트")
	void buyAutoAndManual() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("5000");
		LottoBuyCount lottoBuyCount = lottoPurchaseMoney.getBuyCount(2);
		List<String> manualLottos = new ArrayList<>(Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7"));
		assertThat(LottoStore.buyManualAndAuto(lottoBuyCount, manualLottos).size()).isEqualTo(5);
	}
}
