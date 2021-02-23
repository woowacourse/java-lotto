package lottogame.domain.ticket;

import lottogame.domain.number.LottoNumber;

import java.util.List;

public interface LottoTicket {

    int START_LOTTO_NUMBER = 1;
    int FINISH_LOTTO_NUMBER = 45;
    int COUNT_LOTTO_NUMBER = 6;

    List<LottoNumber> getLottoNumbers();

    boolean isContainNumber(final LottoNumber lottoNumber);

    boolean isAutoTicket();
}
