package com.car.rental.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.car.rental.common.Result;
import com.car.rental.dto.CarQueryDTO;
import com.car.rental.entity.Car;
import com.car.rental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * B端 - 车辆管理控制器
 */
@RestController
@RequestMapping("/admin/car")
@RequiredArgsConstructor
public class CarManageController {

    private final CarService carService;

    /**
     * 车辆列表（分页）
     */
    @GetMapping("/list")
    public Result<IPage<Car>> list(CarQueryDTO dto) {
        IPage<Car> page = carService.queryPage(dto);
        return Result.success(page);
    }

    /**
     * 车辆详情
     */
    @GetMapping("/{id}")
    public Result<Car> detail(@PathVariable Long id) {
        Car car = carService.getDetail(id);
        return Result.success(car);
    }

    /**
     * 添加车辆
     */
    @PostMapping("/add")
    public Result<String> add(@RequestBody Car car) {
        carService.addCar(car);
        return Result.success("添加成功");
    }

    /**
     * 更新车辆
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Car car) {
        carService.updateCar(car);
        return Result.success("更新成功");
    }

    /**
     * 删除车辆
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        carService.deleteCar(id);
        return Result.success("删除成功");
    }

    /**
     * 更新车辆状态
     */
    @PutMapping("/{id}/status")
    public Result<String> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        carService.updateStatus(id, status);
        return Result.success("状态更新成功");
    }
}
