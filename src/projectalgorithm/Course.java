package projectalgorithm;

import java.util.Arrays;

public class Course {

    private String name;
    private String[] prerequisite;
    private int creditHours;
    private int semester;

    public Course(String name, String[] prerequisite, int creditHours, int semester) {
        this.name = name;
        this.prerequisite = prerequisite;
        this.creditHours = creditHours;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String[] prerequisite) {
        this.prerequisite = prerequisite;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Course{" + "name=" + name + ", prerequisite=" + prerequisite + ", creditHours=" + creditHours + ", semester=" + semester + '}';
    }
    
    
    
    
}
