package org.daejeong.reader;

import com.opencsv.CSVReader;
import org.daejeong.event.Meeting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EventCsvReader {
    public List<Meeting> readMeetings(String path) {
        List<Meeting> result = new ArrayList<>();


        // 데이터를 읽는 부분
        List<String[]> read = readAll(path);

        // Meeting 변환 부분
        for (int i = 0; i < read.size(); i++) {
            if (skipHeader(i)) continue;

            String[] each = read.get(i);

            result.add(
                    new Meeting(
                            Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[6],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    ),
                                    ZoneId.of("Asia/Seoul")
                            ),
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[7],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    ),
                                    ZoneId.of("Asia/Seoul")
                            ),
                            new HashSet<>(
                                    Arrays.asList(each[3].split(","))
                            ),
                            each[4],
                            each[5]
                    )
            );
        }

        return result;
    }

    private boolean skipHeader(int i) {
        return i == 0;
    }

    private List<String[]> readAll(String path) {
        InputStream in = getClass().getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

        CSVReader csvReader = new CSVReader(reader);

        try {
            return csvReader.readAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
