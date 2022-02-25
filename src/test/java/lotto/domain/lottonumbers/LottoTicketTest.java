package lotto.domain.lottonumbers;

import static lotto.domain.lottonumbers.LottoTicket.INVALID_LOTTO_NUMBER_COUNT;
import static lotto.domain.lottonumbers.LottoTicket.LOTTO_NUMBER_DUPLICATED;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTicketTest {
    @Test
    @DisplayName("로또 티켓의 번호가 중복이 있을시 예외가 발생한다.")
    void lottoTicketDuplicationException() {
        // given
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(String.valueOf(i)));
        }
        lottoNumbers.add(new LottoNumber("5"));

        // when

        // then
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_DUPLICATED);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumbers")
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

    private static Stream<Arguments> provideInvalidNumbers() {
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
