package examination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

class CashedExaminationTest {
    private final ExaminationWithCount examinationWithCount = new ExaminationWithCount();

    private  final CaсhedExamination cashedExamination = new CaсhedExamination(examinationWithCount);

    @Test
    void testCashedAverageGrade() {
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Ivan", "Ivanov", "Math", 4);
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Petr", "Maratov", "Math", 5);
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Math", 4);
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "English", 5);
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Sveta", "Semenova", "Russian", 5);
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Viktor", "Petrov", "English", 3);
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Viktor", "Petrov", "Math", 5);
        examinationWithCount.putItem(UUID.randomUUID().toString(), new Date(), "Viktor", "Svetlow", "Math", 5);

        Assertions.assertEquals(4.6, cashedExamination.averageGrade("Math"));
        cashedExamination.averageGrade("Math");
        cashedExamination.averageGrade("Math");
        cashedExamination.averageGrade("Math");
        Assertions.assertEquals(1, examinationWithCount.calls);

        cashedExamination.averageGrade("English");
        Assertions.assertEquals(2, examinationWithCount.calls);


        cashedExamination.averageGrade("Russian");
        Assertions.assertEquals(3, examinationWithCount.calls);
        cashedExamination.averageGrade("Math");
        Assertions.assertEquals(4, examinationWithCount.calls);
    }

    private class ExaminationWithCount extends InMemoryExamination {
        private int calls = 0;

        @Override
        public Double averageGrade(String subject) {
            calls++;
            return super.averageGrade(subject);
        }
    }
}

