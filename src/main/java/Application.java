import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Application implements IApplication {
    private final List<Record> recordList;

    public Application() {
        recordList = loadRecords();
    }

    public static void main(String... args) {
        Application application = new Application();

        application.executeQuery01();
        application.executeQuery02();
        application.executeQuery03();
        application.executeQuery04();
        application.executeQuery05();
        application.executeQuery06();
        application.executeQuery07();
        application.executeQuery08();
        application.executeQuery09();
        application.executeQuery10();
        application.executeQuery11();
        application.executeQuery12();
        application.executeQuery13();
        application.executeQuery14();
    }

    public List<Record> loadRecords() {
        List<Record> recordList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Configuration.INSTANCE.dataPath + "records.csv"));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(";");
                int id = Integer.parseInt(entries[0]);
                int weekDay = Integer.parseInt(entries[1]);
                String ticketType = entries[2];
                int source = Integer.parseInt(entries[3]);
                int destination = Integer.parseInt(entries[4]);
                boolean isOffPeak = Boolean.parseBoolean(entries[5]);
                int numberOfRegisteredChildren = Integer.parseInt(entries[6]);
                recordList.add(new Record(id, weekDay, ticketType, source, destination, isOffPeak, numberOfRegisteredChildren));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return recordList;
    }

    public long executeQuery01() {
        System.out.println("--- query 01 (count)");
        System.out.println("SELECT COUNT(*) FROM data");
        long result = recordList.size();
        System.out.println("result : " + result);
        System.out.println();

        return result;
    }

    public void executeQuery02() {
        System.out.println("--- executeQuery02 ---");
        System.out.println();
    }

    public short executeQuery03() {
        System.out.println("--- executeQuery03 ---");
        List<Record> result = recordList.stream()
                .filter(data -> Objects.equals(data.getTicketType(), "W") ||  Objects.equals(data.getTicketType(), "M") || Objects.equals(data.getTicketType(), "Y"))
                .filter(data -> data.getSource() >= 25 && data.getSource() <= 50)
                .filter(data -> data.getDestination() >= 50 && data.getDestination() <= 75)
                .filter(data -> data.isOffPeak())
                .collect(Collectors.toList());
        System.out.println(result.size());
        return (short) result.size();
    }

    public void executeQuery04() {
        System.out.println("--- executeQuery04 ---");
        System.out.println();
    }

    public void executeQuery05() {
        System.out.println("--- executeQuery05 ---");
        System.out.println();
    }

    public void executeQuery06() {
        System.out.println("--- executeQuery06 ---");
        System.out.println();
    }

    public void executeQuery07() {
        System.out.println("--- executeQuery07 ---");
        System.out.println();
    }

    public void executeQuery08() {
        System.out.println("--- executeQuery08 ---");
        List<Record> result = recordList.stream()
                .filter(data -> (data.getWeekDay() == 7))
                .filter(data -> Objects.equals(data.getTicketType(), "S"))
                .filter(data -> data.getSource() == 1 || data.getSource() == 3)
                .filter(data -> data.getDestination() == 1 || data.getDestination() == 3 || data.getDestination() == 5)
                .filter(data -> data.isOffPeak())
                .collect(Collectors.toList());
        System.out.println("sorting by descending source, destination:");
        Comparator<Record> descendingSourceComparator = (Record record01,Record record02) -> (int) (record02.getSource() - record01.getSource());
        Comparator<Record> ascendingDestinationComparator = (record01, record02) -> (int) (record01.getDestination() - record02.getDestination());
        result.sort(ascendingDestinationComparator);
        result.sort(descendingSourceComparator);
        result.forEach(str -> System.out.println(str.getId()));
    }

    public void executeQuery09() {
        System.out.println("--- executeQuery09 ---");
        System.out.println();
    }

    public void executeQuery10() {
        System.out.println("--- executeQuery10 ---");
        System.out.println();
    }

    public void executeQuery11() {
        System.out.println("--- executeQuery11 ---");
        System.out.println();
    }

    public void executeQuery12() {
        System.out.println("--- executeQuery12 ---");
        System.out.println();
    }

    public void executeQuery13() {
        System.out.println("--- executeQuery13 ---");
                System.out.println();
    }

    public void executeQuery14() {
        System.out.println("--- executeQuery14 ---");
        System.out.println();
    }
}