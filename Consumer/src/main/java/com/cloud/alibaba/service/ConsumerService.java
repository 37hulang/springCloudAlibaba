package com.cloud.alibaba.service;

import com.cloud.alibaba.entity.Consumer;
import com.cloud.alibaba.form.ConsumerDelForm;
import com.cloud.alibaba.form.ConsumerPageQForm;
import com.cloud.alibaba.form.ConsumersQueryForm;
import com.cloud.alibaba.vo.ConsumerVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Consumer)表服务接口
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:46:33
 */
public interface ConsumerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Consumer getById(Integer id);

    /**
     * 查询单条数据
     *
     * @param consumer
     * @return 实例对象
     */
    Consumer get(Consumer consumer);

    /**
     * 通过ID集合查询数据
     *
     * @param ids 主键集合
     * @return 实例对象集合
     */
    List<Consumer> getByIds(List<Integer> ids);

    /**
     * 查询多条数据
     *
     * @param consumer
     * @return 实例对象集合
     */
    List<Consumer> list(Consumer consumer);

    /**
     * 查询分页数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    PageInfo<ConsumerVO> pageList(ConsumerPageQForm queryForm);

    /**
     * 查询全部数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    List<Consumer> list(ConsumersQueryForm queryForm);

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 自增id
     */
    Integer insert(Consumer consumer);

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 是否成功
     */
    boolean update(Consumer consumer);

    /**
     * 删除数据
     *
     * @param delForm
     * @return 是否成功
     */
    boolean delete(ConsumerDelForm delForm);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过主键集合删除数据
     *
     * @param ids 主键集合
     * @return 成功执行条数
     */
    Integer deleteById(List<Integer> ids);
}