package dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import model.lottonumber.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoDtoTest {

    @Test
    @DisplayName("로또 번호를 담는 dto 객체를 생성한다.")
    void generateLottoDto() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final Lotto lotto = new Lotto(numbers);

        final LottoDto lottoDto = LottoDto.of(lotto);
        lottoDto.getLottoNumbers()
                .forEach(lottoNumber -> assertThat(numbers).contains(lottoNumber));
    }
}