package meite.example.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * json
 *
 * @author gavin
 * @date 2018/12/25 14:27
 */
public class TestJson {
    static String json = "{\"id\":\"20\",\"name\":\"余胜军\",\"items\":[{\"itemId\":\"20\",\"itemName\":\"每特教育\"},{\"itemId\":\"21\",\"itemName\":\"蚂蚁课堂\"}]}";

    @Test
    public void testJSON() {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String id = jsonObject.getString("id");
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            System.out.println("id:" + object.getString("itemId"));
        }
        User user = JSONObject.parseObject(json, User.class);
        System.out.println(user);
    }
}
