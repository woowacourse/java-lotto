package lotto.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = Arrays.asList(new LottoNumber(6), new LottoNumber(5), new LottoNumber(3)
                , new LottoNumber(4), new LottoNumber(1), new LottoNumber(2));
    }

    @Test
    void LottoNumber_list를_받아_LottoTicket_한_장_생성하기() {
        assertThat(new LottoTicket(lottoNumbers)).isEqualTo(new LottoTicket(lottoNumbers));
    }

    @Test
    void LottoNumber_Set을_받아_LottoTicket_한_장_생성하기() {
        Set<LottoNumber> lottoNumbersInSet = new HashSet<>(lottoNumbers);
        assertThat(new LottoTicket(lottoNumbersInSet)).isEqualTo(new LottoTicket(lottoNumbersInSet));
        assertThat(new LottoTicket(lottoNumbersInSet)).isEqualTo(new LottoTicket(lottoNumbers));
    }

    @Test
    void 순서가_다른_lottoNumber_Set을_정렬해서_LottoTicket을_생성하는지() {
        Set<LottoNumber> lottoNumbersInSet = new LinkedHashSet<>();
        lottoNumbersInSet.add(new LottoNumber(3));
        lottoNumbersInSet.add(new LottoNumber(6));
        lottoNumbersInSet.add(new LottoNumber(4));
        lottoNumbersInSet.add(new LottoNumber(1));
        lottoNumbersInSet.add(new LottoNumber(2));
        lottoNumbersInSet.add(new LottoNumber(5));
        assertThat(new LottoTicket(lottoNumbersInSet)).isEqualTo(new LottoTicket(lottoNumbers));
    }

    @AfterEach
    void tearDown() {
        lottoNumbers = null;
    }
}