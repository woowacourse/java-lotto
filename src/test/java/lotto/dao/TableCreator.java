package lotto.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TableCreator {
    private final static Logger LOGGER = LoggerFactory.getLogger(TableCreator.class);

    private static boolean flag = false;

    private TableCreator() {
    }

    public static void create() throws Exception {
        if (flag) {
            return;
        }
        flag = true;

        File file = new File("src/main/resources/schema.sql");
        FileInputStream fis = new FileInputStream(file);

        String[] querys = getFileContent(fis).split(";");

        for (final String query : querys) {
            //TODO 커넥션 테스트용으로 바꾸기
            Connection connection = DBUtils.getTestConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            LOGGER.info(query);
        }
    }

    public static String getFileContent(FileInputStream fis) throws IOException {
        StringBuilder sb = new StringBuilder();
        Reader r = new InputStreamReader(fis, "UTF-8");  //or whatever encoding
        char[] buf = new char[1024];
        int amt = r.read(buf);
        while (amt > 0) {
            sb.append(buf, 0, amt);
            amt = r.read(buf);
        }
        return sb.toString();
    }
}
