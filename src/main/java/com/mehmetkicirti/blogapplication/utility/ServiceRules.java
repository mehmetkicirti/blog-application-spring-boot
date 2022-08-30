package com.mehmetkicirti.blogapplication.utility;

import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.Response;

import java.util.List;

public class ServiceRules {
    private ServiceRules(){}
    public static Response runLogics(List<Response> logics){
        for (Response logic : logics) {
            if(!logic.getSuccess()){
                return logic;
            }
        }
        return null;
    }
}
