package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {

    @DisplayName("자동으로 로또를 생성하는 기능")
    @Test
    void generateLotto() {
        //given
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        CountOfPurchasingLotto countOfPurchasingLotto = new CountOfPurchasingLotto(3);

        //when
        Lottos lottos = LottoFactory.generates(lottoNumbers -> numbers, countOfPurchasingLotto);

        //then
        assertThat(lottos).isNotNull();
    }
}
