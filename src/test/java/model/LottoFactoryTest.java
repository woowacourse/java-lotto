package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {
    private final LottoFactory lottoFactory = LottoFactory.getInstance();

    @Test
    @DisplayName("수동 로또 구매 테스트")
    void generateLotto() {
        // given
        List<Integer> numbers = List.of(1,2,3,4,5,6);

        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());

        // when
        Lotto lotteries = lottoFactory.generateManual(numbers);

        // then
        assertThat(lotteries.getLottoNumbers()).containsAll(lottoNumbers);
    }
}
