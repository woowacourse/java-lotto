package lotto.domain.lottonumber;

import static lotto.domain.lottonumber.LottoTicket.INVALID_LOTTO_NUMBER_COUNT;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTicketTest {

    @ParameterizedTest
    @MethodSource("provideInvalidLottoNumbers")
    @DisplayName("로또 번호를 리스트로 받을 때 리스트 크기가 6이 아닌 경우 예외가 발생한다.")
    void lottoTicketNot6Numbers(Set<LottoNumber> lottoNumbers) {
        // given & when & then
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_COUNT);
    }

    @Test
    @DisplayName("로또 티켓, 정상 입력시 생성이 된다")
    void createTicket() {
        // given
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(String.valueOf(i)));
        }

        // when & then
        assertThat(new LottoTicket(lottoNumbers)).isNotNull();
    }

    @Test
    @DisplayName("LottoTicket 은 생성시 방어적 복사가 되어야 한다 : Set 생성자")
    void defensive_copy() {
        // given
        Set<LottoNumber> lottoNumberSet = new HashSet<>();
        lottoNumberSet.add(new LottoNumber(1));
        lottoNumberSet.add(new LottoNumber(2));
        lottoNumberSet.add(new LottoNumber(3));
        lottoNumberSet.add(new LottoNumber(4));
        lottoNumberSet.add(new LottoNumber(5));
        lottoNumberSet.add(new LottoNumber(6));

        LottoTicket lottoTicket = new LottoTicket(lottoNumberSet);

        // when
        lottoNumberSet.add(new LottoNumber(7));
        Set<LottoNumber> findLottoNumbers = lottoTicket.lottoNumbers();

        // then
        assertThatThrownBy(() -> findLottoNumbers.add(new LottoNumber(7)))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(findLottoNumbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("LottoTicket 은 생성시 방어적 복사가 되어야 한다 : 문자열 생성자")
    void defensive_copy2() {
        // given
        LottoTicket lottoTicket = new LottoTicket("1, 2, 3, 4, 5, 6");

        // when
        Set<LottoNumber> findLottoNumbers = lottoTicket.lottoNumbers();

        // then

        assertThatThrownBy(() -> findLottoNumbers.add(new LottoNumber(7)))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(findLottoNumbers.size()).isEqualTo(6);
    }

    private static Stream<Arguments> provideInvalidLottoNumbers() {
        Set<LottoNumber> under6Numbers = new HashSet<>();
        Set<LottoNumber> over6Numbers = new HashSet<>();

        for (int lottoNumber = 1; lottoNumber <= 5; lottoNumber++) {
            under6Numbers.add(new LottoNumber(lottoNumber));
        }

        for (int lottoNumber = 1; lottoNumber <= 7; lottoNumber++) {
            over6Numbers.add(new LottoNumber(lottoNumber));
        }

        return Stream.of(
                Arguments.of(under6Numbers),
                Arguments.of(over6Numbers)
        );
    }
}
