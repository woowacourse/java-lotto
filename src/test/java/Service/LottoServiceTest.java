package Service;

import static org.junit.jupiter.api.Assertions.*;

import Model.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class LottoServiceTest {

    @Test
    void 로또_갯수(){
        LottoService lottoService = new LottoService();
        int price = 14000;

        Assertions.assertThat(lottoService.lottoCount(price)).isEqualTo(14);

    }

}