package lotto.dao;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.utils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoDAO {
    private static LottoDAO lottoDAO = new LottoDAO();

    private LottoDAO() {
    }

    public static LottoDAO getInstance() {
        return lottoDAO;
    }

    public void addLotto(Lotto lotto, int round) throws SQLException {
        List<Integer> lottoNumbers = lotto.getLottoNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());

        String query = "INSERT INTO lotto(round, number_1, number_2, number_3, number_4,number_5,number_6) " +
                "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(query);

        pstmt.setInt(1, round);
        pstmt.setInt(2, lottoNumbers.get(0));
        pstmt.setInt(3, lottoNumbers.get(1));
        pstmt.setInt(4, lottoNumbers.get(2));
        pstmt.setInt(5, lottoNumbers.get(3));
        pstmt.setInt(6, lottoNumbers.get(4));
        pstmt.setInt(7, lottoNumbers.get(5));
        pstmt.executeUpdate();
    }

    public List<Lotto> selectByRound(int round) throws SQLException {
        List<Lotto> lottos = new ArrayList<>();

        String query = "SELECT * FROM lotto WHERE round = ?";
        PreparedStatement pstmt = DBUtils.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int firstOfLottoNumber = rs.getInt("lotto_number_1");
            int secondOfLottoNumber = rs.getInt("lotto_number_2");
            int thirdOfLottoNumber = rs.getInt("lotto_number_3");
            int fourthOfLottoNumber = rs.getInt("lotto_number_4");
            int fifthOfLottoNumber = rs.getInt("lotto_number_5");
            int sixthOfLottoNumber = rs.getInt("lotto_number_6");

            Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(
                    firstOfLottoNumber, secondOfLottoNumber, thirdOfLottoNumber,
                    fourthOfLottoNumber, fifthOfLottoNumber, sixthOfLottoNumber)));

            lottos.add(lotto);
        }

        return Collections.unmodifiableList(lottos);
    }
}
