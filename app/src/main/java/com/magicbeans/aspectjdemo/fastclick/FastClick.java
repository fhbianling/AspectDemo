package com.magicbeans.aspectjdemo.fastclick;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author 边凌
 * date 2017/1/19 15:53
 * desc ${TODO}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FastClick {
    /**
     * Click,计时间隔为两次点击之间
     * Invocking,当方法执行完毕后即可再次触发事件，未执行完毕时和Click一样
     */
    enum FilterType {
        Click, Invocking
    }

    long DEFAULT_INTERVAL = 300;

    long value() default DEFAULT_INTERVAL;

    FilterType type() default FilterType.Click;
}
