package domain.lottonumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

public class LottoTicketTest {
    @Test
    void 생성_테스트() {
        SortedSet<LottoNumber> numbers = new TreeSet(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));
        LottoTicket lottoTicket = new LottoTicket(numbers);
        Assertions.assertThat(lottoTicket.contains(LottoNumber.of(1))).isTrue();
        Assertions.assertThat(lottoTicket.contains(LottoNumber.of(2))).isTrue();
        Assertions.assertThat(lottoTicket.contains(LottoNumber.of(3))).isTrue();
        Assertions.assertThat(lottoTicket.contains(LottoNumber.of(4))).isTrue();
        Assertions.assertThat(lottoTicket.contains(LottoNumber.of(5))).isTrue();
        Assertions.assertThat(lottoTicket.contains(LottoNumber.of(6))).isTrue();
    }

    @Test
    void null값_입력시_예외처리() {
        Assertions.assertThatThrownBy(() -> new LottoTicket(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 5, 7})
    void 인자가_6개가_아니면_예외처리(int value) {
        SortedSet<LottoNumber> lottoNumbers = new TreeSet<>();
        for (int i = 0; i < value; i++) {
            lottoNumbers.add(LottoNumber.of(i + 1));
        }
        Assertions.assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
    }

    @Test
    void 비교_테스트() {
        SortedSet<LottoNumber> numbers = new TreeSet(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));
        SortedSet<LottoNumber> numbers2 = new TreeSet(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(9), LottoNumber.of(45), LottoNumber.of(8)));
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoTicket lottoTicket2 = new LottoTicket(numbers);
        LottoTicket lottoTicket3 = new LottoTicket(numbers2);

        Assertions.assertThat(lottoTicket.calculateMatchNumber(lottoTicket2)).isEqualTo(6);
        Assertions.assertThat(lottoTicket.calculateMatchNumber(lottoTicket3)).isEqualTo(3);
    }

    @Test
    void 중복이_있으면_예외_처리() {
        SortedSet<LottoNumber> numbers = new TreeSet(Arrays.asList(
                LottoNumber.of(1), LottoNumber.of(1), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)));
        Assertions.assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
    }
}
