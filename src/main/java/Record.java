public class Record {
    private final int id;
    private final int weekDay;
    private final String ticketType;
    private final int source;
    private final int destination;
    private final boolean isOffPeak;
    private final int numberOfRegisteredChildren;

    public Record(int id, int weekDay, String ticketType, int source, int destination, boolean isOffPeak, int numberOfRegisteredChildren) {
        this.id = id;
        this.weekDay = weekDay;
        this.ticketType = ticketType;
        this.source = source;
        this.destination = destination;
        this.isOffPeak = isOffPeak;
        this.numberOfRegisteredChildren = numberOfRegisteredChildren;
    }

    public int getId() {
        return id;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public boolean isOffPeak() {
        return isOffPeak;
    }

    public int getNumberOfRegisteredChildren() {
        return numberOfRegisteredChildren;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(";").append(weekDay).append(";").append(ticketType).append(";");
        stringBuilder.append(source).append(";").append(destination).append(";").append(isOffPeak).append(";");
        stringBuilder.append(numberOfRegisteredChildren);
        return stringBuilder.toString();
    }
}