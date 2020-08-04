package com.lugew.winsim.controller;


import com.lugew.winsim.controller.response.GeneralResponse;
import com.lugew.winsim.controller.response.Response;
import com.lugew.winsim.entity.Entity;
import com.lugew.winsim.entity.validator.Add;
import com.lugew.winsim.entity.validator.Update;
import com.lugew.winsim.service.Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 抽象控制层
 *
 * @author LuGew
 */
public abstract class AbstractController<T extends Entity<?>, I extends Service<T>> implements Controller<T> {

    @Autowired
    protected I service;

    @Override
    @ApiOperation(value = "列表")
    @PostMapping(value = "/getList")
    public ResponseEntity<?> getList(@RequestBody T entity) {
        return ok(service.getList(entity));
    }

    @Override
    @ApiOperation(value = "分页列表")
    @PostMapping(value = "/getListPage")
    public ResponseEntity<?> getListPage(@RequestBody T entity) {
        return ok(service.getListPage(entity));
    }

    @Override
    @ApiOperation(value = "查找")
    @GetMapping(value = "/get")
    public ResponseEntity<?> get(T entity) {
        return ok(service.getOne(entity));
    }

    @Override
    @ApiOperation(value = "添加")
    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@RequestBody @Validated(value = {Add.class}) T entity) {
        service.insertNotNull(entity);
        return ok();
    }

    @Override
    @ApiOperation(value = "更新")
    @PostMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody @Validated(value = {Update.class}) T entity) {
        service.updateNotNull(entity);
        return ok();
    }

    @Override
    @ApiOperation(value = "删除")
    @PostMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestBody T entity) {
        entity.setDeleted(true);
        service.updateNotNull(entity);
        return ok();
    }

    protected ResponseEntity<?> ok() {
        return ok(null);
    }

    protected <Data> ResponseEntity<?> ok(Data data) {
        Response<Data> response = GeneralResponse.build();
        response.setData(data);
        return ResponseEntity.ok(response);
    }
}
