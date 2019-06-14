package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.LottoTickets;
import lotto.dto.RoundResultDto;

public class LottoRegister {
    public static RoundResultDto register(RoundResultDto roundResultDto) {
        roundResultDto.setRoundNo(RoundDao.findMaxRounNo() + 1);
        RoundDao.addRound(roundResultDto.getRoundNo(), roundResultDto.getMoney());
        LottoTickets lottoTickets = roundResultDto.getLottoTickets();
        for (int index = 0; index < lottoTickets.size(); index++) {
            LottoDao.addLotto(lottoTickets.get(index), roundResultDto.getRoundNo());
        }
        WinningLottoDao.addWinningLotto(roundResultDto.getWinningLotto(), roundResultDto.getRoundNo());
        return roundResultDto;
    }

    public static RoundResultDto getRoundResult(int roundNo) {
        int maxRoundNo = RoundDao.findMaxRounNo();
        if (roundNo < 0 || roundNo > maxRoundNo) {
            throw new IllegalArgumentException("존재하지 않는 Round 입니다.");
        }
        roundNo = roundNo == 0 ? maxRoundNo : roundNo;

        return createRoundResultDto(roundNo, maxRoundNo);
    }

    public static RoundResultDto createRoundResultDto(int roundNo, int maxRoundNo) {
        RoundResultDto roundResultDto = new RoundResultDto();

        roundResultDto.setRoundNo(roundNo);
        roundResultDto.setMaxRoundNo(maxRoundNo);
        roundResultDto.setMoney(RoundDao.findByRoundNo(roundNo));
        roundResultDto.setLottoTickets(LottoDao.findByRoundNo(roundNo));
        roundResultDto.setWinningLotto(WinningLottoDao.findByRoundNo(roundNo));
        roundResultDto.setWinningLottoState(roundResultDto.getWinningLotto().match(roundResultDto.getLottoTickets()));
        roundResultDto.setYield(roundResultDto.getWinningLottoState().getYield(roundResultDto.getMoney()));

        return roundResultDto;
    }
}
