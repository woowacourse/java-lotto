package lotto.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumbersTest {
    @DisplayName("1 이상 45 이하의 로또숫자를 모두 갖도록 초기화되는지 테스트")
    @Test
    void Should_ContainAllLottoNumbers_When_ApplicationStart() {
        for (int i = 1; i <= 45; i++) {
            assertThat(LottoNumbers.get(i)).isEqualTo(new LottoNumber(i));
        }
    }

    @DisplayName("랜덤 로또 번호들 티켓 사이즈 만큼 나오는지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, 100})
    void Should_ThrowException_When_GetOutRangeNumber(int number) {
        assertThatThrownBy(() -> LottoNumbers.get(number))
            .isInstanceOf(InvalidLottoNumberException.class);
    }

    @DisplayName("랜덤 로또 번호들 티켓 사이즈 만큼 나오는지 테스트")
    @Test
    void Should_Return_RandomNumbersTicketSize_When_Get() {
        assertThat(
            LottoNumbers.getRandomNumbersTicketSize().size()
        ).isEqualTo(6);
    }

    @DisplayName("랜덤 로또 번호들 얻은 이후 원래 로또번호들 안섞인 상태인지 테스트")
    @Test
    void Should_Not_Shuffled_When_AfterGetRandomNumbersTicketSize() {
        LottoNumbers.getRandomNumbersTicketSize();
        for (int i = 1; i <= 45; i++) {
            assertThat(LottoNumbers.get(i)).isEqualTo(new LottoNumber(i));
        }
    }
}