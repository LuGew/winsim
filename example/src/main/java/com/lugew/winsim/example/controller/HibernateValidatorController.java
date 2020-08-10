package com.lugew.winsim.example.controller;

import com.lugew.winsim.controller.response.GeneralResponse;
import com.lugew.winsim.controller.response.Response;
import com.lugew.winsim.example.entity.HibernateValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author LuGew
 * @since 2020/7/31
 */
@RestController
@RequestMapping("hibernateValidator")
public class HibernateValidatorController {
    @PostMapping("nameIsNull")
    public ResponseEntity<?> nameIsNull(@RequestBody
                                        @Validated
                                                HibernateValidator entity) {
        return ok();
    }

    @PostMapping("passwordIsNotNull")
    public ResponseEntity<?> passwordIsNotNull(@RequestBody
                                               @Validated({HibernateValidator.Password.class, HibernateValidator.Nullable.class})
                                                       HibernateValidator entity) {
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
