import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int turnTime = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int na = Integer.parseInt(st.nextToken());
            int nb = Integer.parseInt(st.nextToken());

            PriorityQueue<Event> events = new PriorityQueue<>(Comparator.comparing(event -> event.time));
            for (int i = 0; i < na; i++) {
                st = new StringTokenizer(br.readLine());
                String[] start = st.nextToken().split(":");
                String[] end = st.nextToken().split(":");

                // 분으로 초기화
                int startTime = convertTime(start);
                int endTime = convertTime(end);

                events.add(new Event(startTime, "출발", 'B'));
                events.add(new Event(endTime + turnTime, "도착", 'A'));
            }
            for (int i = 0; i < nb; i++) {
                st = new StringTokenizer(br.readLine());
                String[] start = st.nextToken().split(":");
                String[] end = st.nextToken().split(":");

                // 분으로 초기화
                int startTime = convertTime(start);
                int endTime = convertTime(end);

                events.add(new Event(startTime, "출발", 'A'));
                events.add(new Event(endTime + turnTime, "도착", 'B'));
            }
            int[] result = logic(events, na, nb);
            bw.write(String.format("Case #%d: %d %d\n", t, result[0], result[1]));
        }


        bw.flush();
        bw.close();
    }

    private static int[] logic(final PriorityQueue<Event> events, final int na, final int nb) {
        int available_A = 0;
        int available_B = 0;
        int min_A = 0;
        int min_B = 0;
        while (!events.isEmpty()) {
            Event now = events.poll();

            if (now.type.equals("출발")) {
                // B로 간다면
                if (now.to == 'B') {
                    if (available_A > 0) {
                        available_A--;
                    } else {
                        min_A++;
                    }
                } else { // A로 간다면
                    if (available_B > 0) {
                        available_B--;
                    } else {
                        min_B++;
                    }
                }
            } else { // 도착일 경우
                if (now.to == 'A') {
                    available_B++;
                } else {
                    available_A++;
                }
            }
        }

        return new int[]{min_A, min_B};
    }


    private static int convertTime(final String[] time) {
        int H = Integer.parseInt(time[0]);
        int M = Integer.parseInt(time[1]);

        return (H * 60) + M;
    }

    private static class Event {
        private int time;
        private String type;
        private char to;

        public Event(final int time, final String type, final char to) {
            this.time = time;
            this.type = type;
            this.to = to;
        }
    }
}