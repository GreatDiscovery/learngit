package meite.example.json;

import java.util.List;

/**
 * @author gavin
 * @date 2018/12/25 14:51
 */

public class User {
    private Integer id;
    public User() {

    }
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Item> getItems() {
        return items;

    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private String name;
    private List<Item> items;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
class Item{
    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String itemName;
}
