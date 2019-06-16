package lotto.domain;

import lotto.dto.LottoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private final List<Lotto> lottos;

    LottoTickets(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResult getLottoResult(WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos) {
            Rank currentRank = winningLotto.match(lotto);
            lottoResult.add(currentRank);
        }
        return lottoResult;
    }

    public int getLottoTicketsSize() {
        return lottos.size();
    }

    public Lotto getLotto(int index) {
        return lottos.get(index);
    }

    public List<LottoDTO> toDTO(int round) {
        List<LottoDTO> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoDtos.add(lotto.toDTO(round));
        }
        return lottoDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTickets that = (LottoTickets) o;
        return Objects.equals(lottos, that.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }

    @Override
    public String toString() {
        StringBuilder tickets = new StringBuilder();
        for (Lotto lotto : lottos) {
            tickets.append(lotto).append("\n");
        }
        return tickets.toString();
    }
}
