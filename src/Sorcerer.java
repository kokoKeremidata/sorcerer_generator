import java.io.Serializable;

public class Sorcerer implements Serializable {
    private String name;
    private int age;
    private String grade;
    private String ct;
    private int ce;
    private int speed;
    private int strength;
    private int iq;
    private int biq;
    private boolean de;
    private String personality;
    public Sorcerer(String name, int age, String grade, String ct, int ce, int speed, int strength, int iq, int biq, boolean de, String personality) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.ct = ct;
        this.ce = ce;
        this.speed = speed;
        this.strength = strength;
        this.iq = iq;
        this.biq = biq;
        this.de = de;
        this.personality = personality;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getGrade() {
        return grade;
    }
    public String getCt() {
        return ct;
    }
    public int getCe() {
        return ce;
    }
    public int getSpeed() {
        return speed;
    }
    public int getStrength() {
        return strength;
    }
    public int getIq() {
        return iq;
    }
    public int getBiq() {
        return biq;
    }
    public boolean isDe() {
        return de;
    }
    public String getPersonality() {
        return personality;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setCt(String ct) {
        this.ct = ct;
    }
    public void setCe(int ce) {
        this.ce = ce;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setIq(int iq) {
        this.iq = iq;
    }
    public void setBiq(int biq) {
        this.biq = biq;
    }
    public void setDe(boolean de) {
        this.de = de;
    }
    public void setPersonality(String personality) {
        this.personality = personality;
    }

}
