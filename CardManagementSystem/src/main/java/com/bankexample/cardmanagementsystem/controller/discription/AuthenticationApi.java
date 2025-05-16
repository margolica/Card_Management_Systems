package com.bankexample.cardmanagementsystem.controller.discription;


import com.bankexample.cardmanagementsystem.dto.LoginDto;
import com.bankexample.cardmanagementsystem.dto.TokenDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

    public TokenDto login(@RequestBody LoginDto loginDto)
}
