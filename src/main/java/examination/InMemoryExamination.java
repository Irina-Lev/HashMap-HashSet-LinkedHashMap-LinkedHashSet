package examination;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.Comparator.reverseOrder;


public  class InMemoryExamination implements Examination {
    private final Map<String, Exam> examinations = new HashMap<>();

    @Override
    public Exam putItem(String id, Date dateTime, String firstName, String lastName, String subject, Integer grade) {

        Exam newExam = null;
        Exam examOld = null;
        for (Exam exam : examinations.values()) {
            if (exam.firstName().equals(firstName) && exam.lastName().equals(lastName) && exam.subject().equals(subject)) {
                newExam = new Exam(id, dateTime, firstName, lastName, subject, grade, exam.time() + 1);
                examOld = exam;
            }
        }
        if (newExam == null) {
            newExam = new Exam(id, dateTime, firstName, lastName, subject, grade, 1);
            examinations.put(newExam.id(), newExam);
        } else {
            examinations.put(newExam.id(), newExam);
            examinations.remove(examOld.id());
        }

        return newExam;
    }

    @Override
    public Exam getExamOfStudentSubject(String firstName, String lastName, String subject) {
        for (Exam exam : examinations.values()) {
            if (exam.firstName().equals(firstName) && exam.lastName().equals(lastName) && exam.subject().equals(subject)) {
                return exam;
            }
        }

        return null;
    }

    @Override
    public Double averageGrade(String subject) {
        return examinations.values().stream().filter(exam -> exam.subject().equals(subject))
                .map(exam -> exam.grade()).mapToDouble(value -> value).average().getAsDouble();
    }

    @Override
    public List<Exam> getListMoreThenOne() {
        List<Exam> rezList = new ArrayList<>(examinations.values().stream().filter(exam -> exam.time() > 1).toList());
        rezList.sort(Comparator.comparing(Exam::dateTime)
                .thenComparing(Exam::lastName)
                .thenComparing(Exam::firstName)
                .thenComparing(Exam::subject));
        return rezList;
    }

    @Override
    public List<Exam> getListStudents() {
        return examinations.values().stream().filter(exam -> exam.grade()==5).sorted(Comparator.comparing(Exam::dateTime, reverseOrder())).limit(5).toList();
    }

    @Override
    public Set<String> allSubjects() {
        return  examinations.values().stream().map(Exam::subject).collect(Collectors.toSet());
    }


}
