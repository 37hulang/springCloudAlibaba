package com.cloud.alibaba.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @Description 对 org.springframework.cglib.beans.BeanCopier 简单封装,
 * @Author 74716
 * @Date 2020/4/15 13:55
 **/
@Slf4j
public class BeanCopierUtil {

    /**
     * 对象拷贝
     * <p>俩对象属性类型不同不复制</p>
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copy(Object source, Object target) {
        if (!Objects.isNull(source) && !Objects.isNull(target)) {
            BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopier.copy(source, target, null);
        }
    }

    /**
     * 对象拷贝
     * <p>俩对象属性类型不同不复制，要求target类构造方法不是private修饰</p>
     * @param source 源对象
     * @param target 目标实例
     * @param <T> 目标类型
     * @return
     */
    public static <T> T copy(Object source, Class<T> target) {
        if (Objects.isNull(source)) {
            return null;
        }
        T o = null;
        try {
            o = target.newInstance();
        } catch (ReflectiveOperationException e) {
            log.error("对象映射异常", e);
        }
        if (null != o) {
            BeanCopier beanCopier = BeanCopier.create(source.getClass(), o.getClass(), false);
            beanCopier.copy(source, o, null);
        }
        return o;
    }

    /**
     * 集合数据拷贝
     * <p>俩对象属性类型不同不复制</p>
     *
     * @param source 源集合
     * @param target 类实例
     * @param <T> 类型
     * @return
     */
    public static <T> List<T> copyList(List<?> source, Class<T> target) {
        if (Objects.isNull(source)) {
            return null;
        }
        if (CollectionUtils.isEmpty(source)) {
            return new LinkedList<>();
        }
        List<T> result = new LinkedList<>();
        source.forEach(obj -> result.add(copy(obj, target)));
        return result;
    }
}
