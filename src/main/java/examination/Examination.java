package examination;
//      [x]  • добавить сдачу студента(в зачет идет только последняя сдача,хранить все сдачи студента по одному и тому же предмету не нужно)
//      [x]  • получить сдачу студента по имени, фамилии и предмету
//      [x]  • вывод средней оценки по предмету
//      [x]  •  вывод тех студентов,кто сдавал более одного раза
//      [x]  • вывод последних пяти студентов,сдавших на отлично
//      [x]  • вывод всех сданных предметов

import java.util.Date;
import java.util.HashSet;
import java.util.List;


public interface Examination {
    Exam putItem (String id, Date dateTime, String firstName, String lastName, String subject, Integer grade);
    Exam getExamOfStudentSubject(String firstName, String lastName, String subject);

    Double averageGrade(String subject);

    List<Exam> getListMoreThenOne();

    List<Exam> getListStudents();

    HashSet<String> allSubjects();
}
