package lotto.application.lottoticket;

import lotto.application.LottoSession;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.InvalidLottoTicketException;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.LottoTicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoService {
    private static final String ERROR_OUT_OF_BOUND_NUM_OF_MANUAL_LOTTO = "기존에 입력한 로또 수보다 많습니다.";

    private ManualLottoService() {
    }

    public static long getNumOfManualLotto(String num, long numOfLotto) {
        long numOfManualLotto = Long.parseLong(num);
        if (numOfManualLotto > numOfLotto) {
            throw new InvalidLottoTicketException(ERROR_OUT_OF_BOUND_NUM_OF_MANUAL_LOTTO);
        }
        return numOfManualLotto;
    }

    public static LottoTicket getManualLotto(List<String> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(Integer::parseInt)
                .map(LottoNumberPool::valueOf)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicketDto getManualLottoDto(LottoTicket lottoTicket) {
        LottoTicketDto lottoTicketDto = new LottoTicketDto();
        lottoTicketDto.setFirstNum(lottoTicket.getLottoNumber(0).getNumber());
        lottoTicketDto.setSecondNum(lottoTicket.getLottoNumber(1).getNumber());
        lottoTicketDto.setThirdNum(lottoTicket.getLottoNumber(2).getNumber());
        lottoTicketDto.setFourthNum(lottoTicket.getLottoNumber(3).getNumber());
        lottoTicketDto.setFifthNum(lottoTicket.getLottoNumber(4).getNumber());
        lottoTicketDto.setSixthNum(lottoTicket.getLottoNumber(5).getNumber());
        return lottoTicketDto;
    }
}
