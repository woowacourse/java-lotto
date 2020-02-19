package lotto.domain.number;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbersTest {
    List<LottoNumber> lottoNumberList;

    @BeforeEach
    void initiate() {
        lottoNumberList = new ArrayList<>();
        lottoNumberList.add(LottoNumber.of(27));
        lottoNumberList.add(LottoNumber.of(5));
        lottoNumberList.add(LottoNumber.of(7));
        lottoNumberList.add(LottoNumber.of(44));
        lottoNumberList.add(LottoNumber.of(23));
        lottoNumberList.add(LottoNumber.of(45));
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        Assertions.assertThat(new LottoNumbers(lottoNumberList))
                .isInstanceOf(LottoNumbers.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_범위보다_입력의_수가_많은_경우() {
        Assertions.assertThatThrownBy(() -> {
            lottoNumberList.add(LottoNumber.of(30));
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
