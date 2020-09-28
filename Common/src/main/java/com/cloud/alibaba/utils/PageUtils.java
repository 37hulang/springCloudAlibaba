package com.cloud.alibaba.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Supplier;

public final class PageUtils {

    /**
     * 页码
     */
    private static final int PAGENUM = 1;
    /**
     * 页大小
     */
    private static final int PAGESIZE = 20;
    /**
     * 默认最大数据量
     */
    private static final int ALLDATA = 1000;

    /***
    * <b>返回分页数据，第1页，20条数据</b>
    *
    * @Param [supplier]
    * @return com.tospur.middlecommon.page.PageInfo
    * @author xiao
    * @version 1.0
    * @create 2020/3/2
    */
    public static PageInfo generatePage(Supplier<List> supplier){
        return generatePage(supplier, PAGENUM, PAGESIZE);
    }

    /**
     * 返回分页数据
     * @param supplier
     * @param object
     * @return
     */
    public static PageInfo generatePage(Supplier<List> supplier, Object object) {
        Integer page = null;
        Integer rows = null;
        try{
            page = getSuperFieldValueByFieldName("page", object);
            rows = getSuperFieldValueByFieldName("rows", object);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("分页参数异常");
        }

        return generatePage(supplier, page, rows);
    }

    /**
     * 返回分页数据
     * @param supplier 对象
     * @param page 页数
     * @param rows 每页数量
     * @return
     */

    public static PageInfo generatePage(Supplier<List> supplier, Integer page, Integer rows) {
        if(page == null || rows == null){
            throw new IllegalArgumentException("分页参数异常");
        }
        PageHelper.startPage(page, rows);
        // 调用get()方法，此时会调用对象的构造方法，即获得到真正对象
        List list = supplier.get();
        return new PageInfo(list);
    }

    /**
     * 查询返回所有数据(数据量比较大时，建议使用分页查询)
     * @param supplier
     * @return
     */
    public static PageAll generateAll(Supplier<List> supplier){
        // 验证数据总数，是否满足，在返回所有数据
        PageHelper.startPage(PAGENUM, ALLDATA);
        List list = supplier.get();
        return new PageAll(list);
    }

    /**
     * 查询所有数据
     * @param list 结果集
     * @return
     */
    public static <T> PageAll<T> generateAll(List<T> list){
        return new PageAll(list);
    }

    private static int getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return  (int)field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int getSuperFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return  (int)field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void setFieldValueByFieldName(String fieldName, Object object,String value) {
        try {
            // 获取obj类的字节文件对象
            Class c = object.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
