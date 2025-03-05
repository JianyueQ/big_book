package com.big_event;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public  void  getById(Integer id){
        System.out.println("Service get id:"+id);
    }
}
