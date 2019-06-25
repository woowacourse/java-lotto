package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningNumbersWeb implements WinningNumbers {
    private static final String NANUM_LOTTERY_URL = "https://m.dhlottery.co.kr/gameResult.do?method=byWin";

    private final Future<WinningNumbersVO> winningNumbers;

    protected WinningNumbersWeb(int round) {
        this.winningNumbers = Executors.newSingleThreadExecutor().submit(() -> fetchAndParseWinningNumbers(round));
    }

    protected WinningNumbersWeb() {
        this(0);
    }

    private WinningNumbersVO fetchAndParseWinningNumbers(int round) {
        try {
            final String html = httpWinningNumbersRequest(round);
            final List<LottoNumber> numbers = new ArrayList<>();
            final Matcher numbersMatcher = Pattern.compile(">[0-9]+<").matcher(html);
            while (numbersMatcher.find()) {
                String token = numbersMatcher.group();
                numbers.add(LottoNumber.of(token.substring(1, token.indexOf("<"))));
            }
            if (numbers.isEmpty()) {
                if (round != 0) {
                    return fetchAndParseWinningNumbers(0);
                }
                throw new FailedFetchingWinningNumbersFromWebException("Schema has changed.");
            }
            if (round == 0) {
                final Matcher roundMatcher = Pattern.compile("<option value=\"[0-9]+\" >").matcher(html);
                roundMatcher.find();
                String token = roundMatcher.group();
                round = Integer.parseInt(token.substring(15, token.lastIndexOf("\"")));
            }
            return new WinningNumbersVO(numbers, round);
        } catch (Exception e) {
            throw new FailedFetchingWinningNumbersFromWebException(e.getMessage());
        }
    }

    private String httpWinningNumbersRequest(int round) throws Exception {
        final String roundAttr = (round == 0) ? "" : "&drwNo=" + round;
        final StringBuilder html = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) new URL(NANUM_LOTTERY_URL + roundAttr).openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        for (String inputLine = ""; inputLine != null; inputLine = in.readLine()) {
            html.append(inputLine);
        }
        in.close();
        con.disconnect();
        return html.toString();
    }

    @Override
    public List<LottoNumber> mainNumbers() {
        try {
            return this.winningNumbers.get().mainNumbers();
        } catch (InterruptedException | ExecutionException e) {
            return mainNumbers();
        }
    }

    @Override
    public LottoNumber bonusNumber() {
        try {
            return this.winningNumbers.get().bonusNumber();
        } catch (InterruptedException | ExecutionException e) {
            return bonusNumber();
        }
    }

    public int round() {
        try {
            return this.winningNumbers.get().round();
        } catch (InterruptedException | ExecutionException e) {
            return round();
        }
    }
}