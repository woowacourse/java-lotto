package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class LottoFactoryTest {

	@DisplayName("사용자가 입력한 돈에 맞게 로또 생성")
	@Test
	void createLotteries1() {
        int userInputMoney = 13000;
        List<Lotto> lottoList = LottoFactory
                .createLotteries(new Money(userInputMoney, 0), "");
        assertThat(lottoList.size()).isEqualTo(13);

        userInputMoney = 5000;
        List<Lotto> lottoList2 = LottoFactory
                .createLotteries(new Money(userInputMoney, 0), "");
        assertThat(lottoList2.size()).isEqualTo(5);
    }

	@DisplayName("1000원 이하의 금액을 입력했을 경우")
	@Test
	void createLotteries2() {
        int userInputMoneyZero = 0;
        assertThatThrownBy(() -> LottoFactory
                .createLotteries(new Money(userInputMoneyZero, 0), ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("천원 이상의 금액만 가능합니다.");
    }

    @DisplayName("입력값이 비었을 경우")
    @Test
    void nullTest() {
        assertThatThrownBy(() -> LottoFactory
                .createLotteries(null, ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값이 비었습니다.");
    }

    @DisplayName("수동 로또 생성")
    @Test
    void createManualLotto() {
        String[] manualLotto = new String[]{"1,2,3,4,5,6", "7,8,9,10,11,12"};
        List<Lotto> result = new ArrayList<>();
        result.add(new Lotto(IntStream
                .range(1, 7)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toSet())));
        result.add(new Lotto(IntStream
                .range(7, 13)
                .boxed()
                .map(LottoNo::new)
                .collect(Collectors.toSet())));

        assertThat(LottoFactory.createUserLotto(manualLotto)).isEqualTo(result);
    }
}
