package com.mxh.wotvpvpstatisticsbackend.controllers;

import com.mxh.wotvpvpstatisticsbackend.services.OpponentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/opponents")
public class OpponentController {

    OpponentService opponentService;

    OpponentController(OpponentService opponentService){
        this.opponentService = opponentService;
    }
}
