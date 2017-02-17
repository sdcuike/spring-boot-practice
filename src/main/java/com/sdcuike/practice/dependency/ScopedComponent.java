package com.sdcuike.practice.dependency;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class ScopedComponent {
    public int getHashCode() {
        int hashCode = this.hashCode();
        log.info(getClass() + "hashcode:{}", hashCode);
        return hashCode;
    }
}
