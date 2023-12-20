package examination;

import java.util.Calendar;
import java.util.Date;

public record Exam(String id, Date dateTime, String firstName, String lastName, String subject, Integer grade, Integer time) {
}
