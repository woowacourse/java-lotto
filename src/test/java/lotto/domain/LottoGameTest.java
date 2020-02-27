package lotto.domain;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoRound;
import lotto.domain.number.WinningNumbers;
import lotto.domain.result.GameResults;
import lotto.domain.result.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또게임생성() {
        Money money = new Money(10000);
        List<LottoNumber> manualLottoRound1 = new ArrayList<>();
        manualLottoRound1.add(LottoNumber.of(1));
        manualLottoRound1.add(LottoNumber.of(2));
        manualLottoRound1.add(LottoNumber.of(3));
        manualLottoRound1.add(LottoNumber.of(4));
        manualLottoRound1.add(LottoNumber.of(5));
        manualLottoRound1.add(LottoNumber.of(6));

        List<LottoNumber> manualLottoRound2 = new ArrayList<>();
        manualLottoRound2.add(LottoNumber.of(1));
        manualLottoRound2.add(LottoNumber.of(2));
        manualLottoRound2.add(LottoNumber.of(3));
        manualLottoRound2.add(LottoNumber.of(4));
        manualLottoRound2.add(LottoNumber.of(5));
        manualLottoRound2.add(LottoNumber.of(6));

        List<LottoNumber> manualLottoRound3 = new ArrayList<>();
        manualLottoRound3.add(LottoNumber.of(1));
        manualLottoRound3.add(LottoNumber.of(2));
        manualLottoRound3.add(LottoNumber.of(3));
        manualLottoRound3.add(LottoNumber.of(4));
        manualLottoRound3.add(LottoNumber.of(5));
        manualLottoRound3.add(LottoNumber.of(6));

        List<LottoRound> manualLottos = new ArrayList<>();
        manualLottos.add(new LottoRound(manualLottoRound1));
        manualLottos.add(new LottoRound(manualLottoRound2));
        manualLottos.add(new LottoRound(manualLottoRound3));

        LottoGame result = LottoGame.initialize(money, manualLottos);

        assertThat(result).isInstanceOf(LottoGame.class);
        assertThat(result).extracting("money").isEqualTo(new Money(10000));
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 로또_결과_생성() {
        LottoGame lottoGame = LottoGame.initialize(new Money(3000), Collections.emptyList());
        List<LottoNumber> winningNumberArguments = new ArrayList<>();
        winningNumberArguments.add(LottoNumber.of(1));
        winningNumberArguments.add(LottoNumber.of(2));
        winningNumberArguments.add(LottoNumber.of(3));
        winningNumberArguments.add(LottoNumber.of(4));
        winningNumberArguments.add(LottoNumber.of(5));
        winningNumberArguments.add(LottoNumber.of(6));
        // winning number : 1,2,3,4,5,6 bonus number : 44
        WinningNumbers winnngNumber = new WinningNumbers(winningNumberArguments, LottoNumber.of(44));
        GameResults result = lottoGame.calculateResult(winnngNumber);
        assertThat(result).isInstanceOf(GameResults.class);
    }
}