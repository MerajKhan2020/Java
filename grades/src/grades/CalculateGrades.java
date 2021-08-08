package grades;

/**
 * 
 * @author Meraj Khan
 * ITC313 - Programming in Java 2
 * Task 1
 */

public class CalculateGrades {
	private String StudentId;
	private String StudentName;
	private double quiz;
	private double a1;
	private double a2;
	private double a3;
	private double exam;
	private double result;
	private String grade;

	public CalculateGrades() {
		this.quiz = quiz;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.exam = exam;
	};

	public CalculateGrades(double quiz, double a1, double a2, double a3, double exam) {
		this.quiz = quiz;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.exam = exam;

	}

	public CalculateGrades(double result) {
		this.result = result;
	}

	public double getQuiz() {
		return quiz;
	}

	public void setQuiz(double quiz) {
		this.quiz = quiz;
	}

	public double getA1() {
		return a1;
	}

	public void setA1(double a1) {
		this.a1 = a1;
	}

	public double getA2() {
		return a2;
	}

	public void setA2(double a2) {
		this.a2 = a2;
	}

	public double getA3() {
		return a3;
	}

	public void setA3(double a3) {
		this.a3 = a3;
	}

	public double getExam() {
		return exam;
	}

	public void setExam(double exam) {
		this.exam = exam;
	}

	public String getGrade(double result) {
		if (result >= 85) {
			grade = "HD";
		} else if (result >= 75 && result < 85) {
			grade = "DI";
		} else if (result >= 65 && result < 75) {
			grade = "CR";
		} else if (result >= 50 && result < 65) {
			grade = "PS";
		} else if (result < 50) {
			grade = "FL";
		}
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getResult(double quiz, double a1, double a2, double a3, double exam) {
		result = (quiz * 0.05) + (a1 * 0.15) + (a2 * 0.2) + (a3 * 0.10) + (exam * 0.5);
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public String getStudentId() {
		return StudentId;
	}

	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public double getResult() {
		return result;
	}

	public String getGrade() {
		return grade;
	}
}
