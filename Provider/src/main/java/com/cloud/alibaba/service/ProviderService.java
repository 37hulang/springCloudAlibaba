package com.cloud.alibaba.service;

import com.cloud.alibaba.entity.Provider;
import com.cloud.alibaba.form.ProviderDelForm;
import com.cloud.alibaba.form.ProviderPageQForm;
import com.cloud.alibaba.form.ProvidersQueryForm;
import com.cloud.alibaba.vo.ProviderVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Provider)表服务接口
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:47:20
 */
public interface ProviderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Provider getById(Integer id);

    /**
     * 查询单条数据
     *
     * @param provider
     * @return 实例对象
     */
    Provider get(Provider provider);

    /**
     * 通过ID集合查询数据
     *
     * @param ids 主键集合
     * @return 实例对象集合
     */
    List<Provider> getByIds(List<Integer> ids);

    /**
     * 查询多条数据
     *
     * @param provider
     * @return 实例对象集合
     */
    List<Provider> list(Provider provider);

    /**
     * 查询分页数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    PageInfo<ProviderVO> pageList(ProviderPageQForm queryForm);

    /**
     * 查询全部数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    List<Provider> list(ProvidersQueryForm queryForm);

    /**
     * 新增数据
     *
     * @param provider 实例对象
     * @return 自增id
     */
    Integer insert(Provider provider);

    /**
     * 修改数据
     *
     * @param provider 实例对象
     * @return 是否成功
     */
    boolean update(Provider provider);

    /**
     * 删除数据
     *
     * @param delForm
     * @return 是否成功
     */
    boolean delete(ProviderDelForm delForm);

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

    void push(Integer num);
}