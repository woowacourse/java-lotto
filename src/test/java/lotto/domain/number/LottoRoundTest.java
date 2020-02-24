package lotto.domain.number;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRoundTest {
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
        assertThat(new LottoRound(lottoNumberList))
                .isInstanceOf(LottoRound.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_범위보다_입력의_수가_많은_경우() {
        Assertions.assertThatThrownBy(() -> {
            lottoNumberList.add(LottoNumber.of(30));
            new LottoRound(lottoNumberList);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_범위보다_부족한_경우() {
        Assertions.assertThatThrownBy(() -> {
            lottoNumberList.remove(0);
            new LottoRound(lottoNumberList);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자에_null이_들어온_경우() {
        Assertions.assertThatThrownBy(() -> {
            new LottoRound(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void LottoNumber_요소를_가지고_있는지_확인() {
        // given
        List<LottoNumber> lottoNumberList = new ArrayList<>();
        lottoNumberList.add(LottoNumber.of(1));
        lottoNumberList.add(LottoNumber.of(2));
        lottoNumberList.add(LottoNumber.of(3));
        lottoNumberList.add(LottoNumber.of(4));
        lottoNumberList.add(LottoNumber.of(5));
        lottoNumberList.add(LottoNumber.of(6));
        LottoRound lottoRound = new LottoRound(lottoNumberList);
        // when
        boolean isHave = lottoRound.isHave(LottoNumber.of(6));
        boolean isNotHave = lottoRound.isHave(LottoNumber.of(45));
        // then
        assertThat(isHave).isTrue();
        assertThat(isNotHave).isFalse();
    }
}
