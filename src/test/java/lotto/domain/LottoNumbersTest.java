package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import lotto.constant.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {

    @Test
    void 로또_추가() {
        //given
        LottoNumbers lottoNumbers = new LottoNumbers();
        LottoNumber lottoNumber = new LottoNumber(1);

        //when
        lottoNumbers.addLotto(lottoNumber);

        //then
        Assertions.assertThat(lottoNumbers.isMatchExist(lottoNumber)).isTrue();
    }

    @Test
    void 중복된_당첨_번호_찾기() {
        //given
        LottoNumbers lottoNumbers = new LottoNumbers();
        lottoNumbers.addLotto(new LottoNumber(1));
        lottoNumbers.addLotto(new LottoNumber(2));
        lottoNumbers.addLotto(new LottoNumber(3));
        lottoNumbers.addLotto(new LottoNumber(4));
        lottoNumbers.addLotto(new LottoNumber(5));
        lottoNumbers.addLotto(new LottoNumber(5));

        //when & then
        assertThatThrownBy(lottoNumbers::findDuplicateWinningNumber)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_DUPLICATED_ERROR.getMessage());
    }

}