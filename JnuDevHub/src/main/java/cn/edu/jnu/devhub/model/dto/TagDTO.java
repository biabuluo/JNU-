package cn.edu.jnu.devhub.model.dto;

/**
 * @author 小宇
 * @date {2023}-{07}-{13}:{13:55}
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description:
 */
public class TagDTO {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
