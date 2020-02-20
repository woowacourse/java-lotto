package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

    @DisplayName("LottoTicket 에 입력한 숫자가 있는지 테스트")
    @Test
    public void hasNumberTest() {
        LottoTicket lottoTicket = new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThat(lottoTicket.hasNumber(2)).isTrue();
    }

    @DisplayName("LottoTicket 이 갖는 숫자들을 문자로 변환하는 테스트")
    @Test
    public void lottoTicketNumberToString() {
        LottoTicket lottoTicket = new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6)));
        List<String> expected = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Assertions.assertThat(lottoTicket.lottoTicketNumberToString()).isEqualTo(expected);
    }
}
