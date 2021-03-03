package domain;

import domain.lottoGame.Lotto;
import domain.lottoGame.LottoNumber;
import domain.lottoGame.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @DisplayName("Lotto를 생성하는 기능")
    @Test
    void generate() {
        //given
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        //when
        Lotto lotto = new Lotto(new LottoNumbers(lottoNumbers));

        //then
        assertThat(lotto).isNotNull();
    }
}
