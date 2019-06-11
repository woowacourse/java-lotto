package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class WinningNumbersDB implements WinningNumbers {
    private final List<LottoNumber> numbers;

    protected WinningNumbersDB(int round) throws SQLException {
        final DB db = DB.getInstance();
        final String query = "SELECT * FROM winning_numbers WHERE round=?";
        final PreparedStatement pstmt = db.connect().prepareStatement(query);
        pstmt.setInt(1, round);
        List<Integer> fetched = fetchFromResult(pstmt.executeQuery());
        db.close();
        if (fetched.isEmpty()) {
            throw new SQLException();
        }
        numbers = Collections.unmodifiableList(
                fetched.stream()
                .map(x -> LottoNumber.of(x))
                .collect(Collectors.toList())
        );
    }

    protected WinningNumbersDB() throws SQLException {
        this(0);
    }

    private List<Integer> fetchFromResult(ResultSet result) throws SQLException {
        List<Integer> fetched = new ArrayList<>();
        while (result.next()) {
            for (int i = 2; i <= Lotto.NUMBER_OF_PICKS + 2; i++) {
                fetched.add(result.getInt(i));
            }
        }
        return fetched;
    }

    protected static void register(WinningNumbers winningNumbers, int round) {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                registerHelper(winningNumbers, round);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private static void registerHelper(WinningNumbers winningNumbers, int round) throws SQLException {
        final List<LottoNumber> main = winningNumbers.mainNumbers();
        final LottoNumber bonus = winningNumbers.bonusNumber();
        final DB db = DB.getInstance();
        final String query = "INSERT INTO winning_numbers VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        final PreparedStatement pstmt = db.connect().prepareStatement(query);
        pstmt.setInt(1, round);
        for (int i = 0; i < Lotto.NUMBER_OF_PICKS; i++) {
            pstmt.setString(i + 2, main.get(i).toString());
        }
        pstmt.setString(8, bonus.toString());
        pstmt.executeUpdate();
        db.close();
    }

    @Override
    public List<LottoNumber> mainNumbers() {
        return numbers.subList(0, Lotto.NUMBER_OF_PICKS);
    }

    @Override
    public LottoNumber bonusNumber() {
        return numbers.get(Lotto.NUMBER_OF_PICKS);
    }
}