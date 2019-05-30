package lotto.domain.factory;

import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketsFactory {
    private static final String DELIMITER = ",";

    public static List<LottoTicket> create(final int manualAmount, List<String> manualLottoNumbers, final LottoMoney lottoMoney) {
        int totalAmount = lottoMoney.getAmount();
        int randomAmount = totalAmount - manualAmount;
        validateAmount(totalAmount, manualAmount);
        List<LottoTicket> lottoTickets = new ArrayList<>();
        getManualTickets(manualLottoNumbers, lottoTickets);
        getRandomTickets(randomAmount, lottoTickets);
        return lottoTickets;
    }

    private static void validateAmount(int totalAmount, int manualAmount) {
        if (totalAmount < manualAmount) {
            throw new IllegalArgumentException("수동으로 구입할수 있는 갯수는 총 갯수를 초과할수 없습니다.");
        }
    }

    private static void getManualTickets(List<String> manualLottoNumbers, List<LottoTicket> lottoTickets) {
        for (String manualLottoNumber : manualLottoNumbers) {
            lottoTickets.add(generateManual(manualLottoNumber));
        }
    }

    private static LottoTicket generateManual(String manualLottoNumber) {
        List<String> list = Arrays.asList(manualLottoNumber.split(DELIMITER));
        return new LottoTicket(list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private static void getRandomTickets(int randomAmount, List<LottoTicket> lottoTickets) {
        for (int i = 0; i < randomAmount; i++) {
            lottoTickets.add(generateRandom());
        }
    }

    private static LottoTicket generateRandom() {
        List<Integer> randomNumbers = getRandomNumbers();
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LottoTicket.LOTTO_SIZE; i++) {
            lottoNumbers.add(randomNumbers.get(i));
        }
        return new LottoTicket(lottoNumbers);
    }

    private static List<Integer> getRandomNumbers() {
        List<Integer> randomNumbers = IntStream.rangeClosed(LottoNumber.LOTTO_MIN_NUMBER, LottoNumber.LOTTO_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(randomNumbers);
        return randomNumbers;
    }
}
