package com.example.SE_project.payment;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface VNPayService {
    String pay(HttpServletRequest req, Float amount) throws Exception;

    String returnUrl(HttpServletRequest req) throws Exception;
}
