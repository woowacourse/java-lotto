package lottogame.domain.ticket;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lottogame.domain.number.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        Set<LottoNumber> lottoNumberSet = Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toSet());
        this.lottoTicket = new LottoTicket(lottoNumberSet);
    }

    @Test
    @DisplayName("뽑힌 LottoNumber가 6개인지인 확인")
    void testIssuedLottoNumberCount() {
        assertThat(lottoTicket.getLottoNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("LottoNumber가 LottoTicket에 있는지 없는지 확인")
    void testIssueLottoTicketDuplicate() {
        assertThat(lottoTicket.contains(LottoNumber.valueOf(6))).isTrue();
        assertThat(lottoTicket.contains(LottoNumber.valueOf(7))).isFalse();
    }

    @Test
    @DisplayName("로또 번호 개수가 올바르지 않을 때 예외처리")
    void testLottoNumberCountException() {
        Set<LottoNumber> manyCountSet = Stream.of(1, 2, 3, 4, 5, 6, 7)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toSet());
        Set<LottoNumber> lessCountSet = Stream.of(1, 2, 3, 4, 5)
            .map(LottoNumber::valueOf)
            .collect(Collectors.toSet());
        Set<LottoNumber> emptySet = new HashSet<>();
        assertThatThrownBy(() -> new LottoTicket(manyCountSet)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->new LottoTicket(lessCountSet)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->new LottoTicket(emptySet)).isInstanceOf(IllegalArgumentException.class);
    }
}
