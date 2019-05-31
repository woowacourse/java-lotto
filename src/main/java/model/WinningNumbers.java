package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningNumbers {
    private static final String NANUM_LOTTERY_URL = "https://m.dhlottery.co.kr/gameResult.do?method=byWin";

    private static Future<List<LottoNumber>> winningNumbers = init();

    private static Future<List<LottoNumber>> init() {
        return Executors.newSingleThreadExecutor().submit(() -> fetchAndParseWinningNumbers());
    }

    private static List<LottoNumber> fetchAndParseWinningNumbers() {
        try {
            List<LottoNumber> numbers = new ArrayList<>();
            Matcher matcher = Pattern.compile(">[0-9]+<").matcher(httpWinningNumbersRequest());
            while (matcher.find()) {
                String token = matcher.group();
                numbers.add(LottoNumber.of(token.substring(1, token.indexOf("<"))));
            }
            return numbers;
        } catch (Exception e) {
            return fetchAndParseWinningNumbers();
        }
    }

    private static String httpWinningNumbersRequest() throws Exception {
        StringBuilder html = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) new URL(NANUM_LOTTERY_URL).openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        for (String inputLine = ""; inputLine != null; inputLine = in.readLine()) {
            html.append(inputLine);
        }
        in.close();
        con.disconnect();
        return html.toString();
    }

    public static List<LottoNumber> getWinningNumbers() {
        try {
            return winningNumbers.get().subList(0, Lotto.NUMBER_OF_NUMBERS);
        } catch (Exception e) {
            return getWinningNumbers();
        }
    }

    public static LottoNumber getBonusNumber() {
        try {
            return winningNumbers.get().get(Lotto.NUMBER_OF_NUMBERS);
        } catch (Exception e) {
            return getBonusNumber();
        }
    }

    public static void refresh() {
        winningNumbers = init();
    }
}
