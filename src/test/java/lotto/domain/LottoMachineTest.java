package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoMachineTest {
    @DisplayName("인스턴스를 한번만 생성 하는지 (싱글톤 체크)")
    @Test
    void getInstance() {
        LottoMachine thisLottoMachine = LottoMachine.getInstance();
        LottoMachine thatLottoMachine = LottoMachine.getInstance();

        assertThat(thisLottoMachine).isSameAs(thatLottoMachine);
    }

    @DisplayName("정해진 갯수에 맞게 로또들을 자동으로 생성하는지")
    @Test
    void createLottos() {
        AutomaticLottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator();
        LottoMachine lottoMachine = LottoMachine.getInstance();

        List<Lotto> lottos = lottoMachine.createLottos(automaticLottoGenerator, 3);
        assertThat(lottos.size()).isEqualTo(3);
    }
}