package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoContainerTest {
    @Test
    void 로또넘버_생성확인() {
        List<LottoNumber> lottoNumbers = LottoContainer.createLottoNumbers();
        assertThat(lottoNumbers).isEqualTo(LottoContainer.createLottoNumbers());
    }
}
