package com.cloud.alibaba.service.impl;

import com.cloud.alibaba.dao.ConsumerDao;
import com.cloud.alibaba.entity.Consumer;
import com.cloud.alibaba.form.ConsumerDelForm;
import com.cloud.alibaba.form.ConsumerPageQForm;
import com.cloud.alibaba.form.ConsumersQueryForm;
import com.cloud.alibaba.service.ConsumerService;
import com.cloud.alibaba.utils.PageUtils;
import com.cloud.alibaba.vo.ConsumerVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Consumer)表服务实现类
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:46:33
 */
@Slf4j
@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    private ConsumerDao consumerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Consumer getById(Integer id) {
        if (null == id) {
            return null;
        }
        return this.consumerDao.queryById(id);
    }

    /**
     * 查询单条数据
     *
     * @param consumer
     * @return 实例对象
     */
    @Override
    public Consumer get(Consumer consumer) {
        Consumer result = null;
        try {
            result = this.consumerDao.query(consumer);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof TooManyResultsException) {
                log.error(cause.getMessage());
                throw new RuntimeException();
            }
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result;
    }

    /**
     * 通过ID集合查询多条数据
     *
     * @param ids 主键集合
     * @return 实例对象
     */
    @Override
    public List<Consumer> getByIds(List<Integer> ids) {
        if (null == ids || ids.size() == 0) {
            return null;
        }
        List<Integer> collect = ids.stream().distinct().collect(Collectors.toList());
        return this.consumerDao.queryByIds(collect);
    }

    /**
     * 查询多条数据
     *
     * @param consumer
     * @return 实例对象集合
     */
    @Override
    public List<Consumer> list(Consumer consumer) {
        return this.consumerDao.queryAll(consumer);
    }

    /**
     * 查询分页数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    @Override
    public PageInfo<ConsumerVO> pageList(ConsumerPageQForm queryForm) {
        PageInfo pageInfo = PageUtils.generatePage(() -> this.consumerDao.queryAll(null), queryForm);
        return pageInfo;
    }

    /**
     * 查询全部数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    @Override
    public List<Consumer> list(ConsumersQueryForm queryForm) {
        return this.consumerDao.queryAll(null);
    }

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 自增id
     */
    @Override
    public Integer insert(Consumer consumer) {
        return this.consumerDao.insert(consumer) > 0 ? consumer.getId() : 0;
    }

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Consumer consumer) {
        return this.consumerDao.update(consumer) > 0;
    }

    /**
     * 删除数据
     *
     * @param delForm
     * @return 是否成功
     */
    @Override
    public boolean delete(ConsumerDelForm delForm) {
        return this.consumerDao.deleteById(delForm.getId()) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.consumerDao.deleteById(id) > 0;
    }

    /**
     * 通过主键集合删除数据
     *
     * @param ids 主键集合
     * @return 成功执行条数
     */
    @Override
    public Integer deleteById(List<Integer> ids) {
        List<Integer> collect = ids.stream().distinct().collect(Collectors.toList());
        return this.consumerDao.deleteByIds(collect);
    }
}