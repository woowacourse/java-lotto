package domain;

import java.util.*;

public class LottoTicket {
    private static final int MAX_LOTTO_TICKET_SIZE = 6;
    private static final int MIN_LOTTO_NUMBER_RANGE = 1;
    private static final int MAX_LOTTO_NUMBER_RANGE = 45;

    List<Integer> lottoTicket;

    public LottoTicket(List<Integer> lottoTicket) {
        validateLottoTicketSize(lottoTicket);
        validateLottoTicketNumberRange(lottoTicket);
        validateDuplicateLottoNumber(lottoTicket);
        Collections.sort(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    private void validateDuplicateLottoNumber(List<Integer> lottoTicket) {
        Set<Integer> duplicateLottoTicket = new HashSet<>(lottoTicket);
        if (duplicateLottoTicket.size() != lottoTicket.size()) {
            throw new IllegalArgumentException("중복된 로또 숫자를 입력하였습니다.");
        }
    }

    private void validateLottoTicketNumberRange(List<Integer> lottoTicket) {
        if (lottoTicket.stream().anyMatch(this::isLottoNumberRange)) {
            throw new IllegalArgumentException("범위를 벗어나는 로또 숫자를 입력하였습니다.");
        }
    }

    private boolean isLottoNumberRange(int number) {
        return number < MIN_LOTTO_NUMBER_RANGE || number > MAX_LOTTO_NUMBER_RANGE;
    }

    private void validateLottoTicketSize(List<Integer> lottoTicket) {
        if (lottoTicket.size() != MAX_LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 숫자의 개수가 잘못되었습니다.");
        }
    }

    public List<Integer> getLottoTicket() {
        return this.lottoTicket;
    }

    public int getCorrectCount(List<Integer> winningTicket) {
        int correctCount = 0;
        for(int i = 0 ; i < lottoTicket.size() ; i++) {
            if (lottoTicket.get(i) == winningTicket.get(i)) {
                correctCount++;
            }
        }
        return correctCount;
    }
}
