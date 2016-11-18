package org.zp.json; /**
 * The Apache License 2.0
 * Copyright (c) 2016 Victor Zhang
 */

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zp.json.bean.Group;
import org.zp.json.bean.User;

/**
 * 本单元测试展示Fastjson的各种使用场景
 *
 * @author Victor Zhang
 * @date 2016/11/18.
 */
public class FastjsonTest {
    private static Group group;
    private static final String EXPECTED_JSON = "{\"id\":0,\"name\":\"admin\",\"users\":[{\"id\":2,\"name\":\"guest\"},{\"id\":3,\"name\":\"root\"}]}";

    @BeforeClass
    public static void init() {
        group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addUser(guestUser);
        group.addUser(rootUser);
    }

    @Test
    public void testObjectToString() {
        String jsonString = JSON.toJSONString(group);

        Assert.assertEquals(EXPECTED_JSON, jsonString);
        System.out.println(jsonString);
    }

    @Test
    public void testStringToObject() {
        Group tmpGroup = JSON.parseObject(EXPECTED_JSON, Group.class);

        Assert.assertTrue(tmpGroup.equals(group));
    }
}
