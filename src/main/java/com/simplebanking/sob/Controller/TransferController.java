package com.simplebanking.sob.Controller;

import com.simplebanking.sob.Model.TransferImpl;
import com.simplebanking.sob.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    TransferService transferService;
