package lotto.domain.number;

import lotto.domain.result.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ManualLottoRoundsGenertorTest {
    private static final List<LottoRound> manualLottos = new ArrayList<>();
    private static LottoRound manualLottoRound1;
    private static LottoRound manualLottoRound2;
    private static LottoRound manualLottoRound3;

    @BeforeAll
    static void 초기화() {
        List<LottoNumber> round1 = new ArrayList<>();
        round1.add(LottoNumber.of(1));
        round1.add(LottoNumber.of(2));
        round1.add(LottoNumber.of(3));
        round1.add(LottoNumber.of(4));
        round1.add(LottoNumber.of(5));
        round1.add(LottoNumber.of(6));
        manualLottoRound1 = new LottoRound(round1);

        List<LottoNumber> round2 = new ArrayList<>();
        round2.add(LottoNumber.of(5));
        round2.add(LottoNumber.of(12));
        round2.add(LottoNumber.of(16));
        round2.add(LottoNumber.of(22));
        round2.add(LottoNumber.of(25));
        round2.add(LottoNumber.of(32));
        manualLottoRound2 = new LottoRound(round2);

        List<LottoNumber> round3 = new ArrayList<>();
        round3.add(LottoNumber.of(1));
        round3.add(LottoNumber.of(13));
        round3.add(LottoNumber.of(15));
        round3.add(LottoNumber.of(16));
        round3.add(LottoNumber.of(26));
        round3.add(LottoNumber.of(32));
        manualLottoRound3 = new LottoRound(round3);

        manualLottos.add(manualLottoRound1);
        manualLottos.add(manualLottoRound2);
        manualLottos.add(manualLottoRound3);
    }

    @Test
    void generate() {
        Money money = new Money(5000, LottoRoundsGenerator.LOTTO_PRICE);
        ManualLottoRoundsGenertor manualLottoRoundsGenerator = new ManualLottoRoundsGenertor(manualLottos);
        LottoRounds result = manualLottoRoundsGenerator.generate(money);
        assertThat(result.getAllLottoNumbers()).contains(
                manualLottoRound1,
                manualLottoRound2,
                manualLottoRound3
        );
    }
}