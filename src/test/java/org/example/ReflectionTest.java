package org.example;



import org.example.Model.User;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.debug("beans:[{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName()); // 해당 클래스의 이름(path)

        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList())); // 클래스에서 선언된 필드를 가져옴
        logger.debug("User all declared Constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList())); // 클래스에 선언된 생성자를 가져옴
        logger.debug("User all declared Methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList())); // 클래스에 선언된 메소드를 가져옴
    }

    @Test
    void load() throws ClassNotFoundException {
        // 힙 역영에 로드돼 있는 클래스 타입의 객체를 가져오는 3가지 방법
        // 1
        Class<User> clazz = User.class;

        // 2
        User user = new User("ssssudan", "강경민");
        Class<? extends User> clazz2 = user.getClass();

        // 3
        Class<?> clazz3 = Class.forName("org.example.Model.User");

        logger.debug("clazz:[{}]", clazz);
        logger.debug("clazz2:[{}]", clazz2);
        logger.debug("clazz3:[{}]", clazz3);

        // 클래스가 같은 객체인지 비교
        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();
    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example"); // Reflection API   org.example 밑에 있는 모든 클래스에 대해서 찾음

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
