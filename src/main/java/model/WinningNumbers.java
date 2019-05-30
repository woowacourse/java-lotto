package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningNumbers {
    private static final String NANUM_LOTTERY_URL = "https://m.dhlottery.co.kr/gameResult.do?method=byWin";

    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers() {
        List<LottoNumber> numbers = parseWinningNumbers();
        winningNumbers = Collections.unmodifiableList(numbers.subList(0, Lotto.NUMBER_OF_NUMBERS));
        bonusNumber = numbers.get(Lotto.NUMBER_OF_NUMBERS);
    }

    private List<LottoNumber> parseWinningNumbers() {
        try {
            List<LottoNumber> result = new ArrayList<>();
            Matcher matcher = Pattern.compile(">[0-9]+<").matcher(httpWinningNumbersRequest());
            while (matcher.find()) {
                String token = matcher.group();
                result.add(LottoNumber.of(token.substring(1, token.indexOf("<"))));
            }
            return result;
        } catch (Exception e) {
            return parseWinningNumbers();
        }
    }

    private String httpWinningNumbersRequest() throws Exception {
        StringBuilder result = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) new URL(NANUM_LOTTERY_URL).openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        for (String inputLine = ""; inputLine != null; inputLine = in.readLine()) {
            result.append(inputLine);
        }
        in.close();
        con.disconnect();
        return result.toString();
    }

    public Optional<LottoRank> match(List<LottoNumber> numbers) {
        Set<LottoNumber> test = new HashSet<>();
        test.addAll(numbers);
        test.removeAll(winningNumbers);
        return LottoRank.get(Lotto.NUMBER_OF_NUMBERS - test.size(), test.contains(bonusNumber));
    }

    public List<LottoNumber> getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
