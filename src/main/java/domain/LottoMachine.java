package domain;

import domain.strategy.LottoNumberStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    public static final int LOTTO_TICKET_PRICE = 1000;
    private static final int INCREASE_VALUE = 1;

    public List<LottoNumbers> purchaseLottoTickets(List<List<Integer>> lottoNumbers,
                                                  PurchaseType purchaseType,
                                                  LottoNumberStrategy lottoNumberStrategy) {
        List<LottoNumbers> lottos = new ArrayList<>();
        lottos.addAll(purchaseManually(lottoNumbers));
        lottos.addAll(purchaseAutomatically(purchaseType, lottoNumberStrategy));

        return lottos;
    }


    public WinningStat createWinningStat(List<LottoNumbers> lottos, WinLotto winLotto) {
        Map<LottoRank, Integer> ranks = new HashMap<>();
        initializeRank(ranks);

        for (LottoNumbers lotto : lottos) {
            ranks.merge(winLotto.rank(lotto), INCREASE_VALUE, Integer::sum);
        }

        return new WinningStat(ranks);
    }

    private void initializeRank(Map<LottoRank, Integer> ranks) {
        for (LottoRank lottoRank : LottoRank.values()) {
            ranks.put(lottoRank, 0);
        }
    }

    private List<LottoNumbers> purchaseAutomatically(PurchaseType type, LottoNumberStrategy strategy) {
        return IntStream.range(0, type.getAutomaticCount())
                .mapToObj(index -> new LottoNumbers(strategy.generate()))
                .collect(Collectors.toList());
    }

    private List<LottoNumbers> purchaseManually(List<List<Integer>> lottosNumbers) {
        List<LottoNumbers> lottos = new ArrayList<>();

        for (List<Integer> lottoNumbers : lottosNumbers) {
            lottos.add(new LottoNumbers(lottoNumbers.stream()
                    .map(LottoNumber::getInstance)
                    .collect(Collectors.toList())));
        }

        return lottos;
    }
}
