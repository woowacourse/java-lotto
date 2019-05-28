package lotto.model;

import java.util.HashSet;
import java.util.List;

public class LottoTicket {
    private static final int COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET = 6;
    private final List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> lottoNums) {
        if (lottoNums.size() != new HashSet<>(lottoNums).size()) {
            throw new IllegalArgumentException("중복된 번호가 있습니다.");
        }
        if (lottoNums.size() != COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET) {
            throw new IllegalArgumentException("번호의 개수가 6개가 아닙니다.");
        }

        this.lottoTicket = lottoNums;
    }

    @Override
    public String toString(){
        return lottoTicket.toString();
    }
}