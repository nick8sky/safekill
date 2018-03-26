package org.kx.doe.care;

/**
 * create by sunkx on 2018/3/12
 */
public class User {
    private  String name;
    private Long id;
    private Long cc =1l;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCc() {
        return cc;
    }

    public void setCc(Long cc) {
        this.cc = cc;
    }

    public User(String name, Long id) {
        this.name = name;
        this.id = id;
    }
    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", cc=" + cc +
                '}';
    }
}


