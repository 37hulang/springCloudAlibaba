package com.cloud.alibaba.dao;

import com.cloud.alibaba.entity.Consumer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Consumer)表数据库访问层
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:46:31
 */
@Mapper
public interface ConsumerDao {

    /**
     * 通过对象查询单条数据
     * 若查询出多条数据时会报错
     *
     * @param consumer 对象
     * @return 实例对象
     */
    Consumer query(Consumer consumer);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Consumer queryById(Integer id);

    /**
     * 通过ID列表查询多条数据
     *
     * @param ids 主键
     * @return 对象列表
     */
    List<Consumer> queryByIds(List<Integer> ids);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param consumer 实例对象
     * @return 对象列表
     */
    List<Consumer> queryAll(Consumer consumer);

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int insert(Consumer consumer);

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int update(Consumer consumer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键集合删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int deleteByIds(List<Integer> ids);
}