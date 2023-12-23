package examination;


import java.util.Date;
import java.util.List;
import java.util.Set;


public interface Examination {
    Exam putItem (String id, Date dateTime, String firstName, String lastName, String subject, Integer grade);
    Exam getExamOfStudentSubject(String firstName, String lastName, String subject);

    Double averageGrade(String subject);

    List<Exam> getListMoreThenOne();

    List<Exam> getListStudents();

    Set<String> allSubjects();
}
