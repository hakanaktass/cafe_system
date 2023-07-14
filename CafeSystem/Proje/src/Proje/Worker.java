package Proje;

import java.util.Date;

public class Worker {
    private int id;
    private String name;
    private String surname;
    private String title;
    private float salary;
    private Date jobBeginningDate;

    public Worker(int id, String name, String surname, String title, float salary, Date jobBeginningDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.salary = salary;
        this.jobBeginningDate = jobBeginningDate;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                ", jobBeginningDate=" + jobBeginningDate +
                '}';
    }

    public String getInfo(){

        return "Ünvan=> " + title + "\nİsim-Soyisim=> " + name + " " + surname + "\nMaaş=> " + salary + "\nİş Başlangıç Tarihi=>" + jobBeginningDate
                + "\n**********************************************";



    }

    public float getSalary() {
        return salary;
    }
}