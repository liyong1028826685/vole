package com.github.vole.mps.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.vole.common.utils.R;
import com.github.vole.mps.model.entity.SysZuulRoute;
import com.github.vole.mps.service.SysZuulRouteService;
import com.github.vole.portal.common.controller.AbstractController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author liyong
 */
@Controller
@RequestMapping("/mps/route")
public class ZuulRouteController extends AbstractController<SysZuulRouteService, SysZuulRoute> {

    @Autowired
    private SysZuulRouteService sysZuulRouteService;

    @Override
    protected String getTemplatePath() {
        return "ftl/mps/route/";
    }

    @Override
    protected QueryWrapper<SysZuulRoute> getSearchQW(String search, Model model) {
        QueryWrapper<SysZuulRoute> qw = new QueryWrapper<SysZuulRoute>();
        if (StringUtils.isNotBlank(search)) {
            qw.like("service_id", search);
            model.addAttribute("service_id", search);
        }
        return qw;
    }

    @Override
    public String add(Model model) {
        String result = super.add(model);
        sysZuulRouteService.applyZuulRoute();
        return result;
    }

    @Override
    public String edit(String id, Model model) {
        String result = super.edit(id, model);
        sysZuulRouteService.applyZuulRoute();
        return result;
    }

    @Override
    public R<Boolean> delete(String id) {
        R<Boolean> result = super.delete(id);
        sysZuulRouteService.applyZuulRoute();
        return result;
    }

    @Override
    public R<Boolean> deleteBatch(List<String> ids) {
        R<Boolean> result = super.deleteBatch(ids);
        sysZuulRouteService.applyZuulRoute();
        return result;
    }
}
