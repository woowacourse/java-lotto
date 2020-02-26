package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoTicketTest {
    private static Stream<List<LottoNumber>> lottoTicketSetUp() {
        return Stream.of(
                IntStream.of(1, 2, 3, 4, 5).mapToObj(LottoNumber::new).collect(Collectors.toList()),
                IntStream.of(1, 2, 2, 3, 4, 5).mapToObj(LottoNumber::new).collect(Collectors.toList()),
                IntStream.of(1, 2, 3, 4, 5, 6, 7).mapToObj(LottoNumber::new).collect(Collectors.toList())
        );
    }

    @DisplayName("LottoTicket 생성자 유효성 테스트")
    @ParameterizedTest
    @MethodSource("lottoTicketSetUp")
    void lottoTicketConstructorTest(List<LottoNumber> input) {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
