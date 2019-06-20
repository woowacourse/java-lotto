package lotto.application.lottoticket;

import lotto.application.lottoresult.LottoResultService;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.InvalidLottoTicketException;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.LottoTicketsDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketService {
    private static final String ERROR_OUT_OF_BOUND_NUM_OF_MANUAL_LOTTO = "기존에 입력한 로또 수보다 많습니다.";
    private static LottoTicketService lottoTicketService = null;

    private LottoTicketService() {
    }

    public static LottoTicketService getInstance() {
        if (lottoTicketService == null) {
            lottoTicketService = new LottoTicketService();
        }
        return lottoTicketService;
    }

    public long getNumOfManualLotto(String num, String numOfLotto) {
        long numOfManualLotto = Long.parseLong(num);
        if (numOfManualLotto > Long.parseLong(numOfLotto)) {
            throw new InvalidLottoTicketException(ERROR_OUT_OF_BOUND_NUM_OF_MANUAL_LOTTO);
        }
        return numOfManualLotto;
    }

    public LottoTicket makeLottoTicket(List<String> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(Integer::parseInt)
                .map(LottoNumberPool::valueOf)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    public LottoTicketDTO getLottoTicketDto(LottoTicket lottoTicket) {
        return LottoTicketAssembler.getLottoTicketDto(lottoTicket);
    }

    public LottoTickets getAutomaticLottoTickets(String num) {
        long numOfAutomaticLotto = Long.parseLong(num);
        return LottoTicketAssembler.getAutomaticLottoTickets(numOfAutomaticLotto);
    }

    public LottoTicketsDTO getLottoTicketsDto(LottoTickets automaticTickets) {
        return LottoTicketAssembler.getLottoTicketsDto(automaticTickets);
    }

    public LottoNumber makeBonusBall(String num) {
        return LottoNumberPool.valueOf(Integer.parseInt(num));
    }

    public void savePurchasedLottoTicket(LottoTicketDTO lottoTicketDto) {
        LottoResultService lottoResultService = LottoResultService.getInstance();
        int currentRound = lottoResultService.fetchLatestRoundNum();
        saveLottoTicket(currentRound, lottoTicketDto);
    }

    private void saveLottoTicket(int currentRound, LottoTicketDTO lottoTicketDto) {
        LottoTicketDAO lottoTicketDAO = LottoTicketDAO.getInstance();
        lottoTicketDAO.savePurchasedLotto(currentRound, lottoTicketDto);
    }

    public void savePurchasedLottoTickets(LottoTicketsDTO lottoTicketsDto) {
        LottoResultService lottoResultService = LottoResultService.getInstance();
        int currentRound = lottoResultService.fetchLatestRoundNum();
        List<LottoTicketDTO> dtos = lottoTicketsDto.getLottoTicketDTOs();
        for (LottoTicketDTO lottoTicketDto : dtos) {
            saveLottoTicket(currentRound, lottoTicketDto);
        }
    }
}
