package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력
  */
@Target({ElementType.TYPE})  // 클래스에 애노테이션 붙임.
@Retention(RetentionPolicy.RUNTIME) // 유지 기간
public @interface Controller {

}
