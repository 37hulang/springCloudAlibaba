package com.cloud.alibaba.controller.v1;

import com.cloud.alibaba.entity.Consumer;
import com.cloud.alibaba.form.*;
import com.cloud.alibaba.pojo.Resp;
import com.cloud.alibaba.service.ConsumerService;
import com.cloud.alibaba.utils.BeanCopierUtil;
import com.cloud.alibaba.utils.PageAll;
import com.cloud.alibaba.utils.PageUtils;
import com.cloud.alibaba.vo.ConsumerVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <b>(Consumer)表控制层</b>
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:46:34
 */
@Api(tags = "consumer")
@RestController
@RequestMapping("v1/consumer")
public class ConsumerController {

    private final ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键获取详情")
    @PostMapping("/{id}")
    public Resp<ConsumerVO> get(@ApiParam(name = "id", required = true, value = "主键ID") @PathVariable("id") Integer id) {
        Consumer consumer = this.consumerService.getById(id);

        ConsumerVO consumerVO = BeanCopierUtil.copy(consumer, ConsumerVO.class);
        return Resp.success(consumerVO);
    }

    @ApiOperation(value = "详情(未完成)")
    @PostMapping("/get")
    public Resp<ConsumerVO> get(@Valid @RequestBody ConsumerQueryForm queryForm) {
        return null;
    }

    @ApiOperation(value = "分页列表", notes = "查询分页数据")
    @PostMapping("/page")
    public Resp<PageInfo<ConsumerVO>> page(@Valid @RequestBody ConsumerPageQForm queryForm) {
        PageInfo<ConsumerVO> consumerPageInfo = consumerService.pageList(queryForm);
        return Resp.success(consumerPageInfo);
    }

    @ApiOperation(value = "列表", notes = "查询全部数据")
    @PostMapping("/list")
    public Resp<PageAll<ConsumerVO>> list(@Valid @RequestBody ConsumersQueryForm queryForm) {
        List<Consumer> list = consumerService.list(queryForm);

        List<ConsumerVO> consumerVOs = BeanCopierUtil.copyList(list, ConsumerVO.class);
        PageAll<ConsumerVO> consumerVOPageAll = PageUtils.generateAll(consumerVOs);
        return Resp.success(consumerVOPageAll);
    }

    @ApiOperation("新增")
    @PostMapping("/create")
    public Resp create(@Valid @RequestBody ConsumerForm form) {
        Consumer consumer = BeanCopierUtil.copy(form, Consumer.class);
        Integer insert = consumerService.insert(consumer);
        if (insert > 0) {
            return Resp.success(1);
        } else {
            return Resp.fail("失败");
        }
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public Resp update(@Valid @RequestBody ConsumerMForm modifyForm) {
        Consumer consumer = BeanCopierUtil.copy(modifyForm, Consumer.class);
        boolean update = consumerService.update(consumer);
        if (update) {
            return Resp.success(1);
        } else {
            return Resp.fail("失败");
        }
    }

    @ApiOperation(value = "删除", notes = "此操作为物理删除")
    @PostMapping("/delete")
    public Resp delete(@Valid @RequestBody ConsumerDelForm delForm) {
        boolean result = consumerService.delete(delForm);
        if (result) {
            return Resp.success(1);
        } else {
            return Resp.fail("失败");
        }
    }
}