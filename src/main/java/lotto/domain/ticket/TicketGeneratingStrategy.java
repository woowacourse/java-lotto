package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface TicketGeneratingStrategy {
    List<LottoTicket> generateTickets();
}

class ManualGenaratingStarategy implements TicketGeneratingStrategy {
    private List<List<Integer>> numberList;
    private int nums;

    public ManualGenaratingStarategy(int nums, List<List<Integer>> numberList) {
        this.nums = nums;
        this.numberList = numberList;
    }

    public List<LottoTicket> generateTickets() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            lottoTickets.add(generateTicket(numberList.get(i)));
        }
        return lottoTickets;
    }

    private LottoTicket generateTicket(List<Integer> numbers) {
        return LottoTicket.of(generateNumber(numbers));
    }

    private List<LottoNumber> generateNumber(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        numbers.stream().forEach(x -> lottoNumbers.add(LottoNumber.of(x)));
        return lottoNumbers;
    }


}

class AutomaticGenaratingStarategy implements TicketGeneratingStrategy {
    private int nums;

    public AutomaticGenaratingStarategy(int nums) {
        this.nums = nums;
    }

    public List<LottoTicket> generateTickets() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            lottoTickets.add(generateTicket());
        }
        return lottoTickets;
    }

    private LottoTicket generateTicket() {
        return LottoTicket.of(generateNumber());
    }

    private List<LottoNumber> generateNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.addAll(LottoNumber.getLottoNumberPool());
        Collections.shuffle(lottoNumbers);
        lottoNumbers = lottoNumbers.subList(0, LottoTicket.MAX_LOTTO_TICKET_NUMBER);
        return lottoNumbers;
    }

}
