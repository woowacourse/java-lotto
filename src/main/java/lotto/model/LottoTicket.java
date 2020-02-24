package lotto.model;

import lotto.utils.LottoRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int FIRST_LOTTO_NUMBER = 1;
    private List<Integer> lottoTicket = new ArrayList<>();

    public LottoTicket() {
        for (int i = FIRST_LOTTO_NUMBER; i <= LAST_LOTTO_NUMBER; i++) {
            lottoTicket.add(i);
        }
        Collections.shuffle(lottoTicket);
        lottoTicket = lottoTicket.subList(0, 6);
        Collections.sort(lottoTicket);
    }

    public LottoTicket(List<Integer> customizedAutoNumber) {
        this.lottoTicket = customizedAutoNumber;
    }

    public List<Integer> getLottoTicket() {
        return lottoTicket;
    }

    public int matchNumber(WinNumber winNumber) {
        return (int) lottoTicket.stream()
                .filter(winNumber::contains)
                .count();
    }

    public boolean matchesWithBonusBall(int bonusBall) {
        return lottoTicket.contains(bonusBall);
    }
}
