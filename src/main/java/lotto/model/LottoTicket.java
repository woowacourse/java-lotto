package lotto.model;

import lotto.exception.NotSixNumbersException;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {
    private static final int FIRST_INDEX = 0;
    private static final int LOTTO_NUMBER_LENGTH = 6;
    private static final String LOTTO_TICKET_NULL_POINTER_EXCEPTION_MESSAGE = "로또의 번호가 null입니다.";
    private static final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "6개의 숫자를 입력하셔야 합니다.";
    private List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> lottoTicket) {
        checkNullLottoTicket(lottoTicket);
        checkLottoLength(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    private void checkNullLottoTicket(List<LottoNumber> lottoTicket) {
        if (lottoTicket == null) {
            throw new NullPointerException(LOTTO_TICKET_NULL_POINTER_EXCEPTION_MESSAGE);
        }
    }

    private void checkLottoLength(List<LottoNumber> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public static LottoTicket makeAutoTicket(List<LottoNumber> autoTicket) {
        List<LottoNumber> autoNumbers = autoTicket.subList(FIRST_INDEX, LOTTO_NUMBER_LENGTH);
        LottoNumber.sortLottoNumber(autoNumbers);
        return new LottoTicket(autoNumbers);
    }

    public List<Integer> getLottoTicket() {
        List<Integer> lottoTicketNumber = new ArrayList<>();
        for (LottoNumber lottoNumber : lottoTicket) {
            lottoTicketNumber.add(lottoNumber.getLottoNumber());
        }
        return lottoTicketNumber;
    }

    public int matchNumber(WinNumber winNumber) {
        return (int) lottoTicket.stream()
                .filter(winNumber::contains)
                .count();
    }

    public boolean matchesWithBonusBall(BonusBall bonusBall) {
        return lottoTicket.contains(bonusBall.getBonusNumber());
    }

    public boolean matchesWithNumber(LottoNumber inputNumber) {
        return lottoTicket.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(inputNumber));
    }
}
