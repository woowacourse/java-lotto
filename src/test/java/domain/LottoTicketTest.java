package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LottoTicketTest {

    private static Stream<List<Integer>> lottoTicketSetUp() {
        return Stream.of(
                new ArrayList(Arrays.asList(1, 2, 3, 4, 5)),
                new ArrayList(Arrays.asList(1, 2, 2, 3, 4, 5)),
                new ArrayList(Arrays.asList(0, 1, 2, 3, 4, 46)),
                new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @DisplayName("LottoTicket 생성자 테스트")
    @ParameterizedTest
    @MethodSource("lottoTicketSetUp")
    public void lottoTicketConstructorTest(List<Integer> input) {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
