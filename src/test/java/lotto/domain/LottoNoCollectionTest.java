package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LottoNoCollectionTest {
    @Test
    void LottoNo_리스트_생성_테스트() {
        List<LottoNo> lottoNos = LottoNoCollection.createLottoNos();
        assertThat(LottoNo.MAX_NUMBER - LottoNo.MIN_NUMBER + 1).isEqualTo(lottoNos.size());
    }
}
