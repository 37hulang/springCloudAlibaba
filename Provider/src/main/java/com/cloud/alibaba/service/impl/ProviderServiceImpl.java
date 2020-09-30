package com.cloud.alibaba.service.impl;

import com.cloud.alibaba.dao.ProviderDao;
import com.cloud.alibaba.entity.Provider;
import com.cloud.alibaba.form.ProviderDelForm;
import com.cloud.alibaba.form.ProviderPageQForm;
import com.cloud.alibaba.form.ProvidersQueryForm;
import com.cloud.alibaba.service.ProviderService;
import com.cloud.alibaba.utils.PageUtils;
import com.cloud.alibaba.vo.ProviderVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Provider)表服务实现类
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:47:21
 */
@Slf4j
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderDao providerDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.group}")
    private String tg;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Provider getById(Integer id) {
        if (null == id) {
            return null;
        }
        return this.providerDao.queryById(id);
    }

    /**
     * 查询单条数据
     *
     * @param provider
     * @return 实例对象
     */
    @Override
    public Provider get(Provider provider) {
        Provider result = null;
        try {
            result = this.providerDao.query(provider);
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
    public List<Provider> getByIds(List<Integer> ids) {
        if (null == ids || ids.size() == 0) {
            return null;
        }
        List<Integer> collect = ids.stream().distinct().collect(Collectors.toList());
        return this.providerDao.queryByIds(collect);
    }

    /**
     * 查询多条数据
     *
     * @param provider
     * @return 实例对象集合
     */
    @Override
    public List<Provider> list(Provider provider) {
        return this.providerDao.queryAll(provider);
    }

    /**
     * 查询分页数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    @Override
    public PageInfo<ProviderVO> pageList(ProviderPageQForm queryForm) {
        PageInfo pageInfo = PageUtils.generatePage(() -> this.providerDao.queryAll(null), queryForm);
        return pageInfo;
    }

    /**
     * 查询全部数据
     *
     * @param queryForm
     * @return 实例对象集合
     */
    @Override
    public List<Provider> list(ProvidersQueryForm queryForm) {
        return this.providerDao.queryAll(null);
    }

    /**
     * 新增数据
     *
     * @param provider 实例对象
     * @return 自增id
     */
    @Override
    public Integer insert(Provider provider) {
        return this.providerDao.insert(provider) > 0 ? provider.getId() : 0;
    }

    /**
     * 修改数据
     *
     * @param provider 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(Provider provider) {
        return this.providerDao.update(provider) > 0;
    }

    /**
     * 删除数据
     *
     * @param delForm
     * @return 是否成功
     */
    @Override
    public boolean delete(ProviderDelForm delForm) {
        return this.providerDao.deleteById(delForm.getId()) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.providerDao.deleteById(id) > 0;
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
        return this.providerDao.deleteByIds(collect);
    }

    @Override
    public void push(Integer num) {
        try {
            Message msg = MessageBuilder.withPayload(num).
                    setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + num).build();
            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction("spring_trans_topic_group", "rock_topic:all", msg, null);
            log.info(String.format("------ send Transactional msg body = %s , sendResult=%s %n",
                    msg.getPayload(), sendResult.getSendStatus()));
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}