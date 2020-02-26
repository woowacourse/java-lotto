package lotto.domain;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.WinningNumbers;
import lotto.domain.result.GameResults;
import lotto.domain.result.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {
    @Test
    void 로또게임생성() {
        Money money = new Money(3000);
        LottoGame result = LottoGame.initialize(money);
        assertThat(result).isInstanceOf(LottoGame.class);
        assertThat(result).extracting("money").isEqualTo(new Money(3000));
    }

    @Test
    void 로또_결과_생성() {
        LottoGame lottoGame = LottoGame.initialize(new Money(3000));
        List<LottoNumber> winningNumberArguments = new ArrayList<>();
        winningNumberArguments.add(LottoNumber.of(1));
        winningNumberArguments.add(LottoNumber.of(2));
        winningNumberArguments.add(LottoNumber.of(3));
        winningNumberArguments.add(LottoNumber.of(4));
        winningNumberArguments.add(LottoNumber.of(5));
        winningNumberArguments.add(LottoNumber.of(6));
        // winning number : 1,2,3,4,5,6 bonus number : 44
        WinningNumbers winnngNumber = new WinningNumbers(winningNumberArguments, LottoNumber.of(44));
        GameResults result =  lottoGame.calculateResult(winnngNumber);
        assertThat(result).isInstanceOf(GameResults.class);
    }
}