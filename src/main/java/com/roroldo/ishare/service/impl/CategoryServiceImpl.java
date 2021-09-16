package com.roroldo.ishare.service.impl;

import com.roroldo.ishare.dao.CategoryDao;
import com.roroldo.ishare.dao.impl.CategoryDaoImpl;
import com.roroldo.ishare.domain.Category;
import com.roroldo.ishare.service.CategoryService;
import com.roroldo.ishare.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * CategoryService实现类
 * @author 落霞不孤
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0L, -1L);
        List<Category> cs = null;
        if (categorys != null && categorys.size() != 0) {
            System.out.println("redis有缓存数据，从redis中查询...");
            cs = new ArrayList<>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
            JedisUtil.close(jedis);
        } else {
            System.out.println("redis没有缓存数据，从数据库中查询...");
            cs = this.categoryDao.findAll();
            for(int i = 0; i < cs.size(); ++i) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        }
        return cs;
    }

    @Override
    public Category findOne(int cid) {
        return categoryDao.findByCid(cid);
    }

    @Override
    public Category findOne(String cname) {
        return categoryDao.findByCname(cname);
    }
}
