package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 标签service
 */
@Service
public class LabelService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 查询一个标签
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 添加
     */
    public void add(Label label){
        //设置id
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改
     */
    public void update(Label label){ //label有一个数据库存在的ID值
        labelDao.save(label);
    }

    /**
     * 删除
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 构建Specification组合条件
     */
    private Specification<Label> createSpecification(Map searchMap){

        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //1.设计List集合，存放所有的条件
                List<Predicate> preList = new ArrayList<Predicate>();

                //2.组合条件拼装
                if(searchMap.get("labelname")!=null && !searchMap.get("labelname").equals("")){
                    // labelname like '%xxx%'
                    preList.add( cb.like(root.get("labelname").as(String.class),"%"+searchMap.get("labelname")+"%"));
                }
                if(searchMap.get("state")!=null && !searchMap.get("state").equals("")){
                    // state = '0'
                    preList.add( cb.equal(root.get("state").as(String.class),searchMap.get("state")));
                }
                if(searchMap.get("count")!=null){
                    // count = 10
                    preList.add( cb.equal(root.get("count").as(Long.class),searchMap.get("count")));
                }
                if(searchMap.get("recommend")!=null && !searchMap.get("recommend").equals("")){
                    // recommend = '1'
                    preList.add( cb.equal(root.get("recommend").as(String.class),searchMap.get("recommend")) );
                }

                //3.连接条件
                // where labelname like '%xxx%' and state = '0' ....
                Predicate[] preArray = new Predicate[preList.size()];
                /**
                 * preList.toArray(): 把preList集合的每个元素取出，存入一个新的Object[]数组，返回Object[]数组
                 * preList.toArray(preArray): 把preList集合的每个元素取出，存入我们指定的preArray数组，返回preArray数组
                 */
                return cb.and(preList.toArray(preArray));
            }
        };
    }

    /**
     * 条件查询
     */
    public List<Label> findSearch(Map searchMap){
        /**
         * 执行组合条件查询
         */
        Specification<Label> spec = createSpecification(searchMap);
        return labelDao.findAll(spec);
    }

    /**
     * 条件分页查询
     */
    public Page<Label> findSearch(Map searchMap,int page,int size){
        Specification<Label> spec = createSpecification(searchMap);
        //Pageable是分页接口，实现类PageRequest
        // spring data里面page从0开始的
        return labelDao.findAll(spec, PageRequest.of(page-1,size));
    }
}
