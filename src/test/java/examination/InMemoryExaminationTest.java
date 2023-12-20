package examination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class InMemoryExaminationTest {

    private Examination examination;

    @BeforeEach
    void setUp() {
        examination = new InMemoryExamination();
    }

    @Test
    void addFindExam() {
        Exam expected = examination.putItem(UUID.randomUUID().toString(), new Date(), "Ivan", "Ivanov", "Math", 4);
        Exam actual = examination.getExamOfStudentSubject("Ivan", "Ivanov", "Math");
        Assertions.assertEquals(expected, actual);

        Exam expected1 = examination.putItem(UUID.randomUUID().toString(), new Date(), "Ivan", "Ivanov", "Math", 5);
        Exam actual1 = examination.getExamOfStudentSubject("Ivan", "Ivanov", "Math");
        Assertions.assertEquals(expected1, actual1);
    }

    @Test
    void testAverageGrade() {
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Ivan", "Ivanov", "Math", 4);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Petr", "Maratov", "Math", 5);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Math", 4);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "English", 5);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Russian", 5);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Viktor", "Petrov", "English", 3);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Viktor", "Petrov", "Math", 5);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Viktor", "Svetlow", "Math", 5);

        Assertions.assertEquals(4.6, examination.averageGrade("Math"));
        Assertions.assertEquals(4, examination.averageGrade("English"));
    }

    @Test
    void testGetListMoreThenOne() {
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Ivan", "Ivanov", "Math", 4);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Petr", "Maratov", "English", 3);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Math", 4);
        Exam exam = examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Math", 5);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "English", 4);
        Exam exam1 = examination.putItem(UUID.randomUUID().toString(), new Date(), "Petr", "Maratov", "English", 4);
        Exam exam2 = examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "English", 5);


        List<Exam> expectedList = new ArrayList<>(List.of(exam1, exam2, exam));
        expectedList.sort(Comparator.comparing(Exam::dateTime)
                .thenComparing(Exam::lastName)
                .thenComparing(Exam::firstName)
                .thenComparing(Exam::subject));
        List<Exam> actualList = examination.getListMoreThenOne();

//        System.out.println("Expected: ");
//        expectedList.stream().forEach(System.out::println);
//        System.out.println("Actual: ");
//        actualList.stream().forEach(System.out::println);
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void testAllSubjects() {
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Ivan", "Ivanov", "Math", 4);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Petr", "Maratov", "English", 3);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Math", 4);
        examination.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Math", 5);

        Set<String> subjects = new HashSet<>(List.of("Math", "English"));
        Assertions.assertEquals(subjects, examination.allSubjects());
    }

    @Test
    void testGetListStudents() {
        Exam exam1 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 10 , 10, 15), "Ivan", "Ivanov", "Math", 4);
        Exam exam2 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 11 , 10, 16), "Ivan", "Ivanov", "Math", 4);
        Exam exam3 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 12 , 10, 17), "Petr", "Maratov", "English", 3);
        Exam exam4 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 13 , 10, 18), "Sveta", "Semenova", "Math", 4);
        Exam exam5 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 14 , 10, 19), "Sveta", "Semenova", "Math", 5);
        Exam exam6 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 15 , 10, 20), "Sveta", "Semenova", "English", 4);
        Exam exam7 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 16 , 10, 21), "Petr", "Maratov", "English", 4);
        Exam exam8 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 17 , 10, 22), "Sveta", "Semenova", "English", 5);
        Exam exam9 = examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 18 , 10, 23), "Petr", "Phone", "Math", 5);
        Exam exam10 = examination.putItem(UUID.randomUUID().toString(), new Date(2023,11, 2, 10, 24), "Seva", "Ivanov", "Math", 5);
        Exam exam11= examination.putItem(UUID.randomUUID().toString(), new Date(2023, 11, 3 , 10, 25), "Anton", "Maratov", "English", 5);

//        examination.getListStudents().stream().forEach(System.out::println);
        List<Exam> expectedList = new ArrayList<>(List.of(exam9, exam8, exam5, exam11, exam10));

        Assertions.assertEquals(expectedList, examination.getListStudents());
    }
}