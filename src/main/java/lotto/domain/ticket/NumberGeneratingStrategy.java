package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface NumberGeneratingStrategy {
    List<LottoNumber> generateNumber();
}

class ManualGenaratingStarategy implements NumberGeneratingStrategy {
    List<Integer> numberList;

    public ManualGenaratingStarategy(List<Integer> numberList) {
        this.numberList = numberList;
    }

    public List<LottoNumber> generateNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        numberList.stream().forEach(x -> lottoNumbers.add(LottoNumber.of(x)));
        return lottoNumbers;
    }
}

class AutomaticGenaratingStarategy implements NumberGeneratingStrategy {
    public List<LottoNumber> generateNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.addAll(LottoNumber.getLottoNumberPool());
        Collections.shuffle(lottoNumbers);
        lottoNumbers = lottoNumbers.subList(0, LottoTicket.MAX_LOTTO_TICKET_NUMBER);
        return lottoNumbers;
    }
}
