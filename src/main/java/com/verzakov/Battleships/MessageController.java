package com.verzakov.Battleships;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("message")
public class MessageController {

    class test{
        String st1 = "1";

        public String getSt1() {
            return st1;
        }

        public void setSt1(String st1) {
            this.st1 = st1;
        }
    }
    @GetMapping
    public ResponseEntity<?> list(){
        test t = new test();
        return new ResponseEntity<>(555, HttpStatus.OK);
    }

}