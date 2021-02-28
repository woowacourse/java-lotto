package lotto.domain.lottos;

import lotto.domain.lottos.amount.LottoAmount;
import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoWinner;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {

    public static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";
    public static final String LOTTO_AMOUNT_NULL_ERROR_MESSAGE = "로또 숫자를 담은 객체는 null일 수 없습니다.";
    public static final String INPUT_NULL_ERROR_MESSAGE = "수동 숫자 입력은 null 일 수 없습니다.";
    public static final String EMPTY_ERROR_MESSAGE = "로또는 한장 이상 구매해야 합니다.";

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(final List<LottoTicket> lottoTickets) {
        Objects.requireNonNull(lottoTickets, NULL_ERROR_MESSAGE);
        validateEmptyTickets(lottoTickets);
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets createLottoTickets(LottoAmount lottoAmount, List<String> inputLottosManualNumber) {
        Objects.requireNonNull(lottoAmount, LOTTO_AMOUNT_NULL_ERROR_MESSAGE);
        List<LottoTicket> lottoTicketGroup = createManualLottoTickets(inputLottosManualNumber);
        lottoTicketGroup.addAll(createAutoLottoTickets(lottoAmount.getAutoAmount()));
        return new LottoTickets(lottoTicketGroup);
    }

    public static List<LottoTicket> createManualLottoTickets(List<String> inputLottosManualNumber) {
        Objects.requireNonNull(inputLottosManualNumber, INPUT_NULL_ERROR_MESSAGE);
        return inputLottosManualNumber.stream()
                .map(LottoTicket::createManualLottoTicket)
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> createAutoLottoTickets(int autoAmount) {
        return Stream.generate(LottoTicket::createAutoLottoTicket)
                .limit(autoAmount)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(this.lottoTickets);
    }

    private void validateEmptyTickets(final List<LottoTicket> lottoTickets) {
        if (lottoTickets.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    public List<LottoRank> scanLottoTickets(LottoWinner lottoWinner) {
        return this.lottoTickets
                .stream()
                .map(lottoTicket -> lottoTicket.getRank(lottoWinner))
                .collect(Collectors.toList());
    }
}
