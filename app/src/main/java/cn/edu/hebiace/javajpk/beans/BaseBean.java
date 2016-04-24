package cn.edu.hebiace.javajpk.beans;

/**
 * Created by duke on 16-3-14.
 */
public class BaseBean {
    private String name;
    private String path;

    public BaseBean(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public BaseBean() {
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
