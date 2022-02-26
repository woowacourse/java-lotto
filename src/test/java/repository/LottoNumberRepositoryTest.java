package repository;

import static org.assertj.core.api.Assertions.assertThat;

import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberRepositoryTest {
    @Test
    @DisplayName("로또 넘버 레포지토리는 같은 숫자로 요청 시 같은 주소값을 가진 객체를 반환한다")
    void lottoNumberWithSameNumberShouldBeIdentical() {
        // given
        LottoNumber lottoNumberBy45 = LottoNumberRepository.getLottoNumberByInt(45);
        LottoNumber lottoNumberBy45Again = LottoNumberRepository.getLottoNumberByInt(45);

        // when & then
        assertThat(lottoNumberBy45 == lottoNumberBy45Again).isTrue();
    }

    @ParameterizedTest(name = "{0} 를 전달했을 때")
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 넘버 레포지토리는 1~45 숫자를 전달할 경우, 그 값을 포장한 LottoNumber 를 반환한다")
    void getLottoNumberByIntFromLottoNumberRepository(int lottoNumber) {
        // given
        LottoNumber lottoNumberByInt = LottoNumberRepository.getLottoNumberByInt(lottoNumber);

        // when
        int actual = lottoNumberByInt.getNumber();

        // then
        assertThat(actual).isEqualTo(lottoNumber);
    }
}
