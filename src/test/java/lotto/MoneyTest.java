package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    void 구매금액이_숫자인지_검증(){
        assertThatThrownBy(() -> {
            new Money("돈");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 숫자여야합니다.");
    }

    @Test
    void 구매금액이_음수인지_검증() {
        assertThatThrownBy(() -> {
            new Money("-1");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 음수일 수 없습니다.");
    }

    @Test
    void 구매금액이_로또_한_장_가격_보다_낮은지_검증(){
        assertThatThrownBy(() -> {
            new Money("500");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 한장 가격보다 낮은 금액을 입력하셨습니다.");
    }

    @Test
    void 구매금액에_따른_로또_갯수_반환_테스트(){
        Money amount = new Money("14800");
        int lottoCount = amount.getLottoCount();
        assertThat(lottoCount).isEqualTo(14);
    }

//    @Test
//    void 수익률_계산_테스트(){
//        String[] winningNumbers = {"1", "2", "3", "5", "4", "6"};
//        String bonusNumber = "7";
//        WinningNumber winningNumber = new WinningNumber(winningNumbers, bonusNumber);
//
//        List<LottoNumber> myLottoNumbers = new ArrayList<>();
//        myLottoNumbers.add(new LottoNumber(1));
//        myLottoNumbers.add(new LottoNumber(2));
//        myLottoNumbers.add(new LottoNumber(3));
//        myLottoNumbers.add(new LottoNumber(4));
//        myLottoNumbers.add(new LottoNumber(5));
//        myLottoNumbers.add(new LottoNumber(7));
//        Lotto myLotto = new Lotto(myLottoNumbers);
//        Lottos lottos = new Lottos();
//        lottos.addLotto(myLotto);
//
//        LottoResult result = winningNumber.countWinningLotto(lottos);
//        int profitRatio = Money.calculateProfitRatio(result, 1);
//        assertThat(profitRatio).isEqualTo(30000);
//    }

}
