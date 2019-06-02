package lotto.domain;

import lotto.exception.UnmatchedLottoTicketAmountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;
    private final int amountOfCustom;

    public LottoTickets(final String[] customLottoInputs, final int amountOfCustom) {
        validateAmountOfCustomLotto(customLottoInputs, amountOfCustom);

        this.lottoTickets = LottoTicketsFactory.create(customLottoInputs);
        this.amountOfCustom = amountOfCustom;
    }

    private void validateAmountOfCustomLotto(String[] customLottoInputs, int amountOfCustom) {
        if (customLottoInputs.length != amountOfCustom) {
            throw new UnmatchedLottoTicketAmountException("구매할 수동로또의 개수만큼 로또 번호를 입력해야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTickets that = (LottoTickets) o;
        return amountOfCustom == that.amountOfCustom &&
                Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets, amountOfCustom);
    }
}
