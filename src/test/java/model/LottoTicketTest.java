package model;

import static model.LottoTicket.INVALID_LOTTO_NUMBER_COUNT;
import static model.LottoTicket.LOTTO_NUMBER_DUPLICATED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
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
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(LottoNumber.valueOf(i));
        }
        lottoNumbers.add(LottoNumber.valueOf(5));

        // when

        // then
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_DUPLICATED);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumbers")
    @DisplayName("로또 번호를 리스트로 받을 때 리스트 크기가 6이 아닌 경우 예외가 발생한다.")
    void lottoTicketNot6Numbers(List<LottoNumber> lottoNumbers) {
        // given

        // when

        // then
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_COUNT);
    }

    @Test
    @DisplayName("로또 티켓, 정상 입력시 생성이 된다")
    void createTicket() {
        // given
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(LottoNumber.valueOf(i));
        }

        // when

        // then
        assertThat(new LottoTicket(lottoNumbers)).isNotNull();
    }

    private static Stream<Arguments> provideInvalidNumbers() {
        List<LottoNumber> under6Numbers = IntStream.rangeClosed(1, 5)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
        List<LottoNumber> over6Numbers = IntStream.rangeClosed(1, 7)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());

        return Stream.of(
                Arguments.of(under6Numbers),
                Arguments.of(over6Numbers)
        );
    }
}
