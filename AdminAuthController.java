package com.car.rental.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import com.car.rental.common.Result;
import com.car.rental.dto.LoginDTO;
import com.car.rental.entity.Admin;
import com.car.rental.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * B端 - 管理员认证控制器
 */
@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminService adminService;

    /**
     * 管理员登录
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated LoginDTO dto) {
        String token = adminService.login(dto);
        return Result.success("登录成功", token);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("退出成功");
    }

    /**
     * 获取当前管理员信息
     */
    @GetMapping("/admin/info")
    public Result<Admin> getAdminInfo() {
        Admin admin = adminService.getCurrentAdmin();
        return Result.success(admin);
    }
}
