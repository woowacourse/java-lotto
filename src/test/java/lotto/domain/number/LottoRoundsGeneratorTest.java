package lotto.domain.number;

import lotto.domain.result.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRoundsGeneratorTest {
    @Test
    void 로또_생성_test() {
        Money money = new Money(5000);

        List<LottoNumber> manualLottoRound1 = new ArrayList<>();
        manualLottoRound1.add(LottoNumber.of(1));
        manualLottoRound1.add(LottoNumber.of(2));
        manualLottoRound1.add(LottoNumber.of(3));
        manualLottoRound1.add(LottoNumber.of(4));
        manualLottoRound1.add(LottoNumber.of(5));
        manualLottoRound1.add(LottoNumber.of(6));

        List<LottoNumber> manualLottoRound2 = new ArrayList<>();
        manualLottoRound2.add(LottoNumber.of(5));
        manualLottoRound2.add(LottoNumber.of(12));
        manualLottoRound2.add(LottoNumber.of(16));
        manualLottoRound2.add(LottoNumber.of(22));
        manualLottoRound2.add(LottoNumber.of(25));
        manualLottoRound2.add(LottoNumber.of(32));

        List<LottoNumber> manualLottoRound3 = new ArrayList<>();
        manualLottoRound3.add(LottoNumber.of(1));
        manualLottoRound3.add(LottoNumber.of(13));
        manualLottoRound3.add(LottoNumber.of(15));
        manualLottoRound3.add(LottoNumber.of(16));
        manualLottoRound3.add(LottoNumber.of(26));
        manualLottoRound3.add(LottoNumber.of(32));

        List<LottoRound> manualLottos = new ArrayList<>();
        manualLottos.add(new LottoRound(manualLottoRound1));
        manualLottos.add(new LottoRound(manualLottoRound2));
        manualLottos.add(new LottoRound(manualLottoRound3));

        assertThat(LottoRoundsGenerator.createLottoRounds(money, manualLottos)).contains(new LottoRound(manualLottoRound1));
        assertThat(LottoRoundsGenerator.createLottoRounds(money, manualLottos)).contains(new LottoRound(manualLottoRound2));
        assertThat(LottoRoundsGenerator.createLottoRounds(money, manualLottos)).contains(new LottoRound(manualLottoRound3));
        assertThat(LottoRoundsGenerator.createLottoRounds(money, manualLottos)).hasSize(5);

    }
}
