package com.car.rental.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.car.rental.common.Result;
import com.car.rental.entity.Order;
import com.car.rental.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * B端 - 订单管理控制器
 */
@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrderManageController {

    private final OrderService orderService;

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public Result<IPage<Order>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer status) {
        IPage<Order> page = orderService.getAdminOrders(pageNum, pageSize, status);
        return Result.success(page);
    }

    /**
     * 订单详情
     */
    @GetMapping("/{id}")
    public Result<Order> detail(@PathVariable Long id) {
        Order order = orderService.getById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        return Result.success(order);
    }

    /**
     * 确认取车
     */
    @PostMapping("/{orderNo}/pickup")
    public Result<String> confirmPickUp(@PathVariable String orderNo) {
        orderService.confirmPickUp(orderNo);
        return Result.success("取车确认成功");
    }

    /**
     * 确认还车
     */
    @PostMapping("/{orderNo}/return")
    public Result<String> confirmReturn(
            @PathVariable String orderNo,
            @RequestParam Integer mileage,
            @RequestParam(required = false) String damageDesc) {
        orderService.confirmReturn(orderNo, mileage, damageDesc);
        return Result.success("还车确认成功");
    }
}
