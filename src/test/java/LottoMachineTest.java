import domain.LottoMachine;
import domain.Price;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoMachineTest {

    @Test
    void 로또머신은_로또를_티켓_개수만큼_생성할_수_있다() {
        //given
        Price price = new Price("10000");
        LottoMachine lottoMachine = new LottoMachine();

        //when & then
        assertThat(lottoMachine.generateLottos(price.getTicketCount()).getLottos().size()).isEqualTo(price.getTicketCount());
    }
}
