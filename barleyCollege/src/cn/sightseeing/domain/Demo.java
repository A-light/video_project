package cn.sightseeing.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import cn.sightseeing.utils.CommonUtils;

public class Demo {
	@Test
	public void fun1() throws Exception {
		String className = "cn.sightseeing.domain.User";
		Class cls = Class.forName(className);
		Object bean = cls.newInstance();

		BeanUtils.setProperty(bean, "username", "bob");
		BeanUtils.setProperty(bean, "gender", "男");
		BeanUtils.setProperty(bean, "shenfen", "学生");
		BeanUtils.setProperty(bean, "age", 23);

		String age = BeanUtils.getProperty(bean, "age");
		System.out.println("年龄：" + age);
		System.out.println(bean);

	}

	/**
	 * @throws Exception
	 *             Map:{"username":"zhangsan"，"pwd":"123"}
	 */
	@Test
	public void fun2() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "zhangsan");
		map.put("pwd", "123");

		User user = new User();
		BeanUtils.populate(user, map);
		System.out.println(user);

	}

	@Test
	public void fun3() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "zhangsan");
		map.put("pwd", "123");
		User user=CommonUtils.toBean(map, User.class);
		System.out.println(user);
	}
}
