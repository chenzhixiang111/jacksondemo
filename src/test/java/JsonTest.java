import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author czx
 * @Description
 * @Version 2018-10-26 9:44
 */
public class JsonTest {

    //测试将复杂的json转类对象
    @Test
    public void testJsonToObj() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Chal chal = objectMapper.readValue(new File("data.json"), Chal.class);
        System.out.println(chal);
    }


    //测试jackson将复杂的json转map
    @Test
    public void analysis() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> jsonmap= objectMapper.readValue(new File("data.json"),
                new TypeReference<Map<String, Object>>(){});
//        System.out.println(jsonmap);
        System.out.println(((ArrayList)jsonmap.get("ChannelDataList")).get(0)
                .getClass());//class java.util.LinkedHashMap
    }

    //测试jackson将json转map
    @Test
    public void testToMap() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);//美化输出

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", 25);
        map.put("name", "lalala");
        map.put("interests", new String[]{"sleep", "eat"});

        String text = objectMapper.writeValueAsString(map);
        System.out.println(text);

        Map<String, Object> map2 = objectMapper.readValue(text, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map2);

        JsonNode root = objectMapper.readTree(text);
        String name = root.get("name").asText();
        System.out.println(name);

        //这里有个坑，如果JsonNode是数组，用asText是没有任何输出的，所以需要get到具体的某个数组元素
        //我猜测是asText会调用toString(),而数组的toString是无意义的。所以才没有输出。
        String interest = root.get("interests").get(0).asText();
        System.out.println(interest);
        //JsonNode也是可以用来遍历的,它自己有实现了迭代器
//        root.get("interests").forEach(s -> System.out.println(s.asText()));
    }


    //测试使用jackson的ObjectMapper基本用法
    @Test
    public void testJackson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Friend friend = new Friend("czx", 25);

        //对象序列化为字符串
        String text = objectMapper.writeValueAsString(friend);
        //对象序列化为文件
        objectMapper.writeValue(new File("friend.json"), friend);
        //对象序列化为字节流
        byte[] bytes = objectMapper.writeValueAsBytes(friend);
        System.out.println(text);
//        for (byte b : bytes){
//            System.out.println(b);
//        }
        //将字符串反序列化为对象
        Friend newFriend1 = objectMapper.readValue(text, Friend.class);
        System.out.println(newFriend1);
        //读取文件流转为对象
        Friend newFriend2 = objectMapper.readValue(new File("friend.json"), Friend.class);
        System.out.println(newFriend2);
        //读取字节流转为对象
        Friend newFriend3 = objectMapper.readValue(bytes, Friend.class);
        System.out.println(newFriend3);
    }
}
