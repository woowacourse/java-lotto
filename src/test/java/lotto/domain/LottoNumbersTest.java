package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbersTest {
    List<LottoNumberGroup> lottoNumberList;

    @BeforeEach
    void initiate() {
        lottoNumberList = new ArrayList<>();
        lottoNumberList.add(LottoNumberGroup.ONE);
        lottoNumberList.add(LottoNumberGroup.FIVE);
        lottoNumberList.add(LottoNumberGroup.NINE);
        lottoNumberList.add(LottoNumberGroup.THREE);
        lottoNumberList.add(LottoNumberGroup.FORTY_FIVE);
        lottoNumberList.add(LottoNumberGroup.TWELVE);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        Assertions.assertThat(new LottoNumbers(lottoNumberList))
                .isInstanceOf(LottoNumbers.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_범위를_많은_경우() {
        Assertions.assertThatThrownBy(() -> {
            lottoNumberList.add(LottoNumberGroup.THIRTY);
            new LottoNumbers(lottoNumberList);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_범위보다_부족한_경우() {
        Assertions.assertThatThrownBy(() -> {
            lottoNumberList.remove(0);
            new LottoNumbers(lottoNumberList);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자에_null이_들어온_경우() {
        Assertions.assertThatThrownBy(() -> {
            new LottoNumbers(null);
        }).isInstanceOf(NullPointerException.class);
    }
}
