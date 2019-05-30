package lotto.domain;

import lotto.domain.ShuffleRule.FixShuffle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {
    private static List<LottoNumber> actual;

    @BeforeEach
    void setUp() {
        actual = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());
    }

    @Test
    void 로또_티켓_생성() {
        assertThat(LottoTicket.createLottoTicket(actual)).isEqualTo(LottoFactory.getLottoTicket(new FixShuffle()));
    }
}