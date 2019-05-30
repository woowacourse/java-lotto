package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LottoResult implements Iterable<Map.Entry<LottoRank, Integer>> {
    private static final String NANUM_LOTTERY_URL = "https://m.dhlottery.co.kr/gameResult.do?method=byWin";

    private final Map<LottoRank, Integer> rankings;
    private final double earningRate;

    /*
    protected LottoResult(List<Lotto> lottos, Set<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this.rankings = processResult(lottos, winningNumbers, bonusNumber);
        this.earningRate = getTotalEarnings().getEarningRate(new Money(Lotto.PRICE * lottos.size()));
    }
    */

    protected LottoResult(List<Lotto> lottos) {
        List<LottoNumber> currentWinningNumbers = getCurrentWinningNumbers();
        Set<LottoNumber> winningNumbers = new HashSet<>(currentWinningNumbers.subList(0, Lotto.NUMBER_OF_NUMBERS));
        LottoNumber bonusNumber = currentWinningNumbers.get(Lotto.NUMBER_OF_NUMBERS);
        this.rankings = processResult(lottos, winningNumbers, bonusNumber);
        this.earningRate = getTotalEarnings().getEarningRate(new Money(Lotto.PRICE * lottos.size()));
    }

    private Map<LottoRank, Integer> processResult(List<Lotto> lottos, Set<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> result = new LinkedHashMap<LottoRank, Integer>() {{
            Stream.of(LottoRank.values()).forEach(rank -> put(rank, 0));
        }};
        lottos.forEach(lotto -> {
            LottoRank rank = lotto.match(winningNumbers, bonusNumber);
            result.put(rank, result.get(rank) + 1);
        });
        result.remove(LottoRank.NONE);
        return Collections.unmodifiableMap(result);
    }

    private List<LottoNumber> getCurrentWinningNumbers() {
        try {
            List<LottoNumber> result = new ArrayList<>();
            Matcher matcher = Pattern.compile(">[0-9]+<").matcher(httpWinningNumbersRequest());
            while (matcher.find()) {
                String token = matcher.group();
                result.add(LottoNumber.of(token.substring(1, token.indexOf("<"))));
            }
            return result;
        } catch (Exception e) {
            return getCurrentWinningNumbers();
        }
    }

    private String httpWinningNumbersRequest() throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(NANUM_LOTTERY_URL).openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder result = new StringBuilder();
        for (String inputLine = ""; inputLine != null; inputLine = in.readLine()) {
            result.append(inputLine);
        }
        in.close();
        con.disconnect();
        return result.toString();
    }

    private Money getTotalEarnings() {
        return new Money(
                rankings.entrySet().stream()
                .map(x -> x.getKey().getPrize().getAmount() * x.getValue())
                .reduce(0, Integer::sum)
        );
    }

    public double getEarningRate() {
        return earningRate;
    }

    public Iterator<Map.Entry<LottoRank, Integer>> iterator() {
        return rankings.entrySet().iterator();
    }
}