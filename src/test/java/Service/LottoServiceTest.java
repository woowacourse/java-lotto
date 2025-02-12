package Service;

import Model.LottoResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Test
    void 로또_갯수(){
        LottoService lottoService = new LottoService();
        int price = 14000;

        Assertions.assertThat(lottoService.lottoCount(price)).isEqualTo(14);

    }

    @Test
    void 로또_결과() {
        int price = 14000;
        LottoResult.addCount(3, false);
        Assertions.assertThat(LottoResult.FIFTH.getCount()).isEqualTo(1);
        double result = lottoService.lottoRateOfReturn(price);
        Assertions.assertThat(result).isEqualTo(0.35);
    }
}