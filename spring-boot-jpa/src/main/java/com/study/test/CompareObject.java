package com.study.test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;  

public class CompareObject {
	public static void main(String[] args) {
		Dog dog1 = new Dog("大师兄的dog", true, true);
		Dog dog2 = new Dog("大师兄的dog", false, false);
		contrastObj(dog1,dog2);
	}

	private static void contrastObj(Object obj1, Object obj2) {
		if (obj1 instanceof Dog && obj2 instanceof Dog) {
			Dog pojo1 = (Dog) obj1;
			Dog pojo2 = (Dog) obj2;
			List<String> textList = new ArrayList<String>();
			try {
				Class clazz = pojo1.getClass();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
					Method getMethod = pd.getReadMethod();
					Object o1 = getMethod.invoke(pojo1);
					Object o2 = getMethod.invoke(pojo2);
					String s1 = o1 == null ? "" : o1.toString();// 避免空指针异常
					String s2 = o2 == null ? "" : o2.toString();// 避免空指针异常
					// 思考下面注释的这一行：会bug的，虽然被try catch了，程序没报错，但是结果不是我们想要的
					// if (!o1.toString().equals(o2.toString())) {
					if (!s1.equals(s2)) {
						textList.add("不一样的属性：" + field.getName() + " 属性值：[" + s1 + "," + s2 + "]");
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			for (Object object : textList) {
				System.out.println(object);
			}
		}
	}
}
