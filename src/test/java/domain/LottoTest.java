package domain;

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

    @DisplayName("구입금액에 따라 Lotto 개수를 반환하는 기능")
    @Test
    void calculateLottoNumber() {
        //given
        Money money = new Money(1_000);

        //when
        int lottoNumber = Lotto.calculateLottoNumber(money);

        //then
        assertThat(lottoNumber).isEqualTo(1);
    }
}
