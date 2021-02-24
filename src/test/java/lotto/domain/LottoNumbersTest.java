package lotto.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumbersTest {
    @DisplayName("범위 내의 인덱스로 캐싱되어있는 로또번호 꺼내기 테스트")
    @Test
    void Should_Return_ExpectedLottoNumber_When_GetIndexInRange() {
        for (int i = 0; i < 45; i++) {
            assertThat(LottoNumbers.get(i)).isEqualTo(new LottoNumber(i + 1));
        }
    }

    @DisplayName("범위 밖의 인덱스로 캐싱되어있는 로또번호 꺼내기 에러 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 45, 100})
    void Should_ThrowException_When_GetIndexOutRange(int index) {
        assertThatThrownBy(() -> LottoNumbers.get(index))
            .isInstanceOf(IndexOutOfBoundsException.class);
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
        for (int i = 0; i < 45; i++) {
            assertThat(LottoNumbers.get(i).getNumber()).isEqualTo(i + 1);
        }
    }
}