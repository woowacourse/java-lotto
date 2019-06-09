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

public class AutoWinningNumbers implements WinningNumbers {
    private static final String NANUM_LOTTERY_URL = "https://m.dhlottery.co.kr/gameResult.do?method=byWin";

    private final Future<List<LottoNumber>> winningNumbers;

    public AutoWinningNumbers() {
        this.winningNumbers = Executors.newSingleThreadExecutor().submit(this::fetchAndParseWinningNumbers);
    }

    private List<LottoNumber> fetchAndParseWinningNumbers() {
        try {
            List<LottoNumber> numbers = new ArrayList<>();
            Matcher matcher = Pattern.compile(">[0-9]+<").matcher(httpWinningNumbersRequest());
            while (matcher.find()) {
                String token = matcher.group();
                numbers.add(LottoNumber.of(token.substring(1, token.indexOf("<"))));
            }
            return numbers;
        } catch (Exception e) {
            throw new RuntimeException("로또 당첨 번호 서버 접속에 실패하였습니다.");
        }
    }

    private String httpWinningNumbersRequest() throws Exception {
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