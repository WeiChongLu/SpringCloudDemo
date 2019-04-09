package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
/**
 * 标签Controller
 */
@RequestMapping("/label")
@RestController
@CrossOrigin  // 解决跨域问题 jsonp
@RefreshScope
public class LabelController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LabelService labelService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 查询所有标签
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll()throws Exception{
       InetAddress address = InetAddress.getLocalHost();
        System.out.println("进来了Label");
        System.out.println("==你所在服务的IP：" +
                address +"-----" + request.getServerPort() );
        System.out.println("111111");
        System.out.println("222222");
        System.out.println("333333");
        System.out.println("444444");
        System.out.println("devTest+666");
        System.out.println("devTest+777");
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());

    }

    /**
     * 查询一个标签
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id) throws Exception{
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("==你所在服务的IP：" +
                address +"-----" + request.getServerPort() );
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }


    /**
     * 添加标签
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 修改标签
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label,@PathVariable String id){
        //设置id
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 标签的条件查询
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        List<Label> list = labelService.findSearch(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    /**
     * 标签的条件分页查询
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.findSearch(searchMap,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pageData.getTotalElements(),pageData.getContent()));
    }
}
