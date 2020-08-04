package com.lugew.winsim.service;


import com.github.pagehelper.PageInfo;
import com.lugew.winsim.entity.Entity;
import com.lugew.winsim.mapper.Mapper;
import com.lugew.winsim.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 抽象业务逻辑
 *
 * @author LuGew
 */
public abstract class AbstractService<T extends Entity<?>, I extends Mapper<T>> implements Service<T> {

    @Autowired
    protected I mapper;

    private void preInsert(T entity) {
        entity.setCreator(getCurrentUserId());
        entity.setCreateDateTime(LocalDateTimeUtil.now());
    }

    public abstract Long getCurrentUserId();

    private void preUpdate(T entity) {
        entity.setModifier(getCurrentUserId());
        entity.setModifyDateTime(LocalDateTimeUtil.now());
    }

    @Override
    public int insert(T entity) {
        preInsert(entity);
        return mapper.insert(entity);
    }

    @Override
    public int insertNotNull(T entity) {
        preInsert(entity);
        return mapper.insertSelective(entity);
    }

    @Override
    public int update(T entity) {
        preUpdate(entity);
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        preUpdate(entity);
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByExample(T entity, Object example) {
        preUpdate(entity);
        return mapper.updateByExample(entity, example);
    }

    @Override
    public int updateByExampleNotNull(T entity, Object example) {
        preUpdate(entity);
        return mapper.updateByExampleSelective(entity, example);
    }

    @Override
    public int delete(T entity) {
        preUpdate(entity);
        return mapper.delete(entity);
    }

    @Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int deleteByExample(Object example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public T getOneByExample(Object example) {
        return mapper.selectOneByExample(example);
    }

    @Override
    public int getCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public List<T> getList(T entity) {
        return mapper.select(entity);
    }

    @Override
    public List<T> getListByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    @Override
    public T getOne(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public T get(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public boolean exists(Object key) {
        return mapper.existsWithPrimaryKey(key);
    }

    @Override
    public PageInfo<T> getListPage(T entity) {
        return new PageInfo<>(getList(entity));
    }
}