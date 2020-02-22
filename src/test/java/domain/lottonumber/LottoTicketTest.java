package domain.lottonumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

public class LottoTicketTest {
    private TreeSet makeTestTicket() {
        return new TreeSet(Arrays.asList(
                LottoNumberFactory.getInstance(1), LottoNumberFactory.getInstance(2), LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(4), LottoNumberFactory.getInstance(5), LottoNumberFactory.getInstance(6))
        );
    }

    @Test
    void 생성_테스트() {
        SortedSet<LottoNumber> numbers = makeTestTicket();
        LottoTicket lottoTicket = new LottoTicket(numbers);
        for (int i = 1; i <= 6; i++) {
            Assertions.assertThat(lottoTicket.contains(LottoNumberFactory.getInstance(i))).isTrue();
        }
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
            lottoNumbers.add(LottoNumberFactory.getInstance(i + 1));
        }
        Assertions.assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
    }

    @Test
    void 비교_테스트() {
        SortedSet<LottoNumber> numbers = makeTestTicket();
        SortedSet<LottoNumber> numbers2 = new TreeSet(Arrays.asList(
                LottoNumberFactory.getInstance(1), LottoNumberFactory.getInstance(2), LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(7), LottoNumberFactory.getInstance(8), LottoNumberFactory.getInstance(9))
        );;
        LottoTicket lottoTicket = new LottoTicket(numbers);
        LottoTicket lottoTicket2 = new LottoTicket(numbers);
        LottoTicket lottoTicket3 = new LottoTicket(numbers2);

        Assertions.assertThat(lottoTicket.calculateMatchNumber(lottoTicket2)).isEqualTo(6);
        Assertions.assertThat(lottoTicket.calculateMatchNumber(lottoTicket3)).isEqualTo(3);
    }

    @Test
    void 중복이_있으면_예외_처리() {
        SortedSet<LottoNumber> numbers = new TreeSet(Arrays.asList(
                LottoNumberFactory.getInstance(1), LottoNumberFactory.getInstance(2), LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(3), LottoNumberFactory.getInstance(4), LottoNumberFactory.getInstance(5))
        );
        Assertions.assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인자의 갯수가 올바르지 않습니다.");
    }
}
