package lotto.service;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoRank;
import lotto.domain.LottoTicket;
import lotto.domain.WinningTicket;
import lotto.repository.LottoRepository;

public class WinningService {

    private final LottoRepository lottoRepository;

    public WinningService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public List<LottoRank> compare(WinningTicket winningTicket) {
        List<LottoTicket> lottoTickets = lottoRepository.get();
        return lottoTickets.stream().map(winningTicket::compare).collect(Collectors.toList());
    }
}
