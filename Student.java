public class Student {
    private String id;
    private String name;
    private float marks;

    public Student() {
    }

    public Student(String id, String name, float marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public float getMarks() {return marks;}

    public void setMarks(float marks) {this.marks = marks;}

    public String getAcademicAbility() {
        String kq = null;
        if (this.getMarks() >= 0 && this.getMarks() <= 10) {
            if (this.getMarks() < 5) {
                kq = "Fail";
            } else if (this.getMarks() <= 6.5) {
                kq = "Medium";
            } else if (this.getMarks() <= 7.5) {
                kq = "Good";
            } else if (this.getMarks() < 9) {
                kq = "Very Good";
            } else {
                kq = "Excellent";
            }
        } else {
            kq = "Something wrong";
        }
        return kq;
    }
}




