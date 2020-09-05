package com.example.demo1;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

// proxyMode = ScopedProxyMode.TARGET_CLASS : 이 빈을 프록시 기반의 클래스로 감싸라.
@Component @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Proto {

}
