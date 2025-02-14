import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoMachineTest {

    @Test
    void 로또머신은_로또_구매개수를_갖고있다() {
        //given
        LottoMachine lottoMachine = new LottoMachine(new Price("10000"));
        //when & then
        assertThat(lottoMachine.getTicket()).isEqualTo(10);
    }

    @Test
    void 로또머신은_로또를_티켓_개수만큼_생성할_수_있다() {
        //given
        LottoMachine lottoMachine = new LottoMachine(new Price("10000"));

        //when & then
        assertThat(lottoMachine.generateLottos().getLottos().size()).isEqualTo(lottoMachine.getTicket());
    }
}
