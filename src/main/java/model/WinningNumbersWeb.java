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

    private final Future<List<LottoNumber>> winningNumbers;
    private final int round;

    protected WinningNumbersWeb(int round) {
        this.round = round;
        this.winningNumbers = Executors.newSingleThreadExecutor().submit(this::fetchAndParseWinningNumbers);
    }

    protected WinningNumbersWeb() {
        this(0);
    }

    private List<LottoNumber> fetchAndParseWinningNumbers() {
        try {
            final List<LottoNumber> numbers = new ArrayList<>();
            final Matcher matcher = Pattern.compile(">[0-9]+<").matcher(httpWinningNumbersRequest());
            while (matcher.find()) {
                String token = matcher.group();
                numbers.add(LottoNumber.of(token.substring(1, token.indexOf("<"))));
            }
            return Collections.unmodifiableList(numbers);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private String httpWinningNumbersRequest() throws Exception {
        final String roundAttr = this.round == 0 ? "" : "&drwNo=" + this.round;
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
            return this.winningNumbers.get().subList(0, Lotto.NUMBER_OF_PICKS);
        } catch (InterruptedException | ExecutionException e) {
            return mainNumbers();
        }
    }

    @Override
    public LottoNumber bonusNumber() {
        try {
            return this.winningNumbers.get().get(Lotto.NUMBER_OF_PICKS);
        } catch (InterruptedException | ExecutionException e) {
            return bonusNumber();
        }
    }
}