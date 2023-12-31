package classes.function;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Flow.Processor;
import java.io.File;
import classes.exam.*;
import classes.question.Question;
import classes.question.QuestionCountDetail;
import classes.question.QuestionSet;
import classes.repository.ExamRecordRepository;
import classes.repository.ExamRepository;
import classes.repository.QuestionRepository;
import classes.subject.Subject;
import classes.util.CheckInput;
import classes.util.Constant;
import classes.util.CustomDate;
import classes.util.FileHandling;
import classes.util.Menu;
import classes.user.*;

public class StudentFunction {

    public static void doExam(Scanner sc, Student Student) {
        System.out.println("Enter the subject's ID");

        Subject subject = new Subject(sc.nextLine());

        CustomDate date = new CustomDate();
        System.out.println("Enter exam's day:");
        String newDate = sc.nextLine();
        System.out.println("Enter exam's month:");
        String newMonth = sc.nextLine();
        System.out.println("Enter exam's year:");
        String newYear = sc.nextLine();

        date.setDay(newDate);
        date.setMonth(newMonth);
        date.setYear(newYear);

        ExamRepository examRepository = new ExamRepository(Constant.dataPath.Exams_Dir, subject, Student.getClazz(),
                date);

        ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
                Student.getClazz(), date);

        File exam = new File(examRepository.getPath() + "exam" + Student.getOrnum());
        File examRecord = new File(examRecordRepository.getPath() + "exam" + Student.getOrnum());

        if (exam.exists()) {
            if (examRecord.exists()) {
                System.out.println("You have done all the exam");
            } else if (!Session.checkDate(date)) {
                System.out.println("Not the time for doing exam");
            } else {
                Session.run(sc, examRepository, examRecordRepository, "exam" + Student.getOrnum(),
                        Student.getUsername());
            }
        } else {
            System.out.println("You don't have any exams");
        }

    }

    public static void DisplayResult(Scanner sc, Student Student) {
        System.out.println("Enter the subject's ID");
        Subject subject = new Subject(sc.nextLine());
        CustomDate date = new CustomDate();
        System.out.println("Enter exam's day:");
        String newDate = sc.nextLine();
        System.out.println("Enter exam's month:");
        String newMonth = sc.nextLine();
        System.out.println("Enter exam's year:");
        String newYear = sc.nextLine();

        date.setDay(newDate);
        date.setMonth(newMonth);
        date.setYear(newYear);

        ExamRecordRepository examRecordRepository = new ExamRecordRepository(Constant.dataPath.ExamRecords_Dir, subject,
                Student.getClazz(), date);
        examRecordRepository.displayExamRecord("exam" + Student.getOrnum());

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student student = new Student("IT20224001", "0123456", "student",
                new UserInfo("v_v_d", 2002, "male", "0912332131"));
        StudentFunction.doExam(sc, student);
        // StudentFunction.DisplayResult(sc, student);
    }
}