package cn.nb.bean;

public class Teacher {
    private Integer id;
    private String name;
    private Integer tid;

    public Teacher() {
    }

    public Teacher(Integer id, String name, Integer tid) {
        this.id = id;
        this.name = name;
        this.tid = tid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                '}';
    }
}
