import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataGenerator {
    private final ArrayList<String> ticketTypeList;
    private final ArrayList<Record> recordList;

    public DataGenerator() {
        ticketTypeList = new ArrayList<>();
        recordList = new ArrayList<>();
    }

    /* PLEASE DO NOT MODIFY
    public static void main(String... args) {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.initTicketTypeList();
        dataGenerator.generateRecords();
        dataGenerator.generateToCSVFile();
    }*/

    public void initTicketTypeList() {
        ticketTypeList.add("S");
        ticketTypeList.add("D");
        ticketTypeList.add("W");
        ticketTypeList.add("M");
        ticketTypeList.add("Y");
    }

    public void generateRecords() {
        for (int i = 0; i < Configuration.INSTANCE.maximumNumberOfRecords; i++) {
            int randomWeekDay = Configuration.INSTANCE.randomNumberGenerator.nextInt(1, 7);
            String randomTicketType = ticketTypeList.get(Configuration.INSTANCE.randomNumberGenerator.nextInt(0, ticketTypeList.size() - 1));
            int randomSource = Configuration.INSTANCE.randomNumberGenerator.nextInt(1, 100);

            int randomDestination;
            do {
                randomDestination = Configuration.INSTANCE.randomNumberGenerator.nextInt(1, 100);
            } while (randomDestination == randomSource);

            boolean randomIsOffPeak = Configuration.INSTANCE.randomNumberGenerator.nextBoolean();

            Record record = new Record(i + 1, randomWeekDay, randomTicketType, randomSource, randomDestination, randomIsOffPeak, Configuration.INSTANCE.randomNumberGenerator.nextInt(0, 3));
            recordList.add(record);
        }
    }

    public void generateToCSVFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Configuration.INSTANCE.dataPath + "records.csv"));

            for (int i = 0; i < Configuration.INSTANCE.maximumNumberOfRecords; i++) {
                bufferedWriter.write(recordList.get(i).toString() + Configuration.INSTANCE.lineSeparator);
            }

            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}