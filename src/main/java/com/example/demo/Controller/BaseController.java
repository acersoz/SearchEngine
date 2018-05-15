package com.example.demo.Controller;

import com.example.demo.Model.Exception.InvalidUrlException;
import com.example.demo.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("searchengine/api")
public class BaseController {

    @Autowired
    private BaseService baseService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateData() {
        try {
            String savedUrl = baseService.updateData();
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUrl);
        }
        catch (InvalidUrlException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity testDocuments() {
        int x;
        x = baseService.Test();
        return ResponseEntity.status(HttpStatus.CREATED).body(x);
    }
}
