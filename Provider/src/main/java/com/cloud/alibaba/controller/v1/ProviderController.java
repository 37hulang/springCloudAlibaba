package com.cloud.alibaba.controller.v1;

import com.cloud.alibaba.entity.Provider;
import com.cloud.alibaba.form.*;
import com.cloud.alibaba.pojo.Resp;
import com.cloud.alibaba.service.ProviderService;
import com.cloud.alibaba.utils.BeanCopierUtil;
import com.cloud.alibaba.utils.PageAll;
import com.cloud.alibaba.utils.PageUtils;
import com.cloud.alibaba.vo.ProviderVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <b>(Provider)表控制层</b>
 *
 * @author 74716
 * @version 1.0
 * @create 2020-09-28 09:47:22
 */
@Api(tags = "provider")
@RestController
@RequestMapping("v1/provider")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键获取详情")
    @PostMapping("/{id}")
    public Resp<ProviderVO> get(@ApiParam(name = "id", required = true, value = "主键ID") @PathVariable("id") Integer id) {
        Provider provider = this.providerService.getById(id);

        ProviderVO providerVO = BeanCopierUtil.copy(provider, ProviderVO.class);
        return Resp.success(providerVO);
    }

    @ApiOperation(value = "详情(未完成)")
    @PostMapping("/get")
    public Resp<ProviderVO> get(@Valid @RequestBody ProviderQueryForm queryForm) {
        return null;
    }

    @ApiOperation(value = "分页列表", notes = "查询分页数据")
    @PostMapping("/page")
    public Resp<PageInfo<ProviderVO>> page(@Valid @RequestBody ProviderPageQForm queryForm) {
        PageInfo<ProviderVO> providerPageInfo = providerService.pageList(queryForm);
        return Resp.success(providerPageInfo);
    }

    @ApiOperation(value = "列表", notes = "查询全部数据")
    @PostMapping("/list")
    public Resp<PageAll<ProviderVO>> list(@Valid @RequestBody ProvidersQueryForm queryForm) {
        List<Provider> list = providerService.list(queryForm);

        List<ProviderVO> providerVOs = BeanCopierUtil.copyList(list, ProviderVO.class);
        PageAll<ProviderVO> providerVOPageAll = PageUtils.generateAll(providerVOs);
        return Resp.success(providerVOPageAll);
    }

    @ApiOperation("新增")
    @PostMapping("/create")
    public Resp create(@Valid @RequestBody ProviderForm form) {
        Provider provider = BeanCopierUtil.copy(form, Provider.class);
        Integer insert = providerService.insert(provider);
        if (insert > 0) {
            return Resp.success(1);
        } else {
            return Resp.fail("失败");
        }
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public Resp update(@Valid @RequestBody ProviderMForm modifyForm) {
        Provider provider = BeanCopierUtil.copy(modifyForm, Provider.class);
        boolean update = providerService.update(provider);
        if (update) {
            return Resp.success(1);
        } else {
            return Resp.fail("失败");
        }
    }

    @ApiOperation(value = "删除", notes = "此操作为物理删除")
    @PostMapping("/delete")
    public Resp delete(@Valid @RequestBody ProviderDelForm delForm) {
        boolean result = providerService.delete(delForm);
        if (result) {
            return Resp.success(1);
        } else {
            return Resp.success("失败");
        }
    }
}