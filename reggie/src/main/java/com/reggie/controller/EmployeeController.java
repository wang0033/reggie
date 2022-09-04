package com.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.common.R;
import com.reggie.domain.Employee;
import com.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author 86182
 * @create 2022/8/13 17:56
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param employee
     * @param request
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
//         1、将页面提交的密码进行md5加密处理
        String password = employee.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

//        2、根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee employeeServiceOne = employeeService.getOne(lambdaQueryWrapper);

//        3、如果没有查询到则返回登录失败结果
        if (employeeServiceOne == null){
            return R.error("登录失败");
        }


//        4、如果有则比对密码，如果不一致则返回登录失败结果
        if (!employeeServiceOne.getPassword().equals(digestPassword)){
            return R.error("登录失败");
        }


//        5、查看员工状态，如果已禁用，则返回员工已禁用结果
        if (employeeServiceOne.getStatus() == 0){
            return R.error("账号已禁用");
        }


//        6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", employeeServiceOne.getId());
        return R.success(employeeServiceOne);
    }

    /**
     * 员工登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中保存的当前员工id
        request.getSession().removeAttribute("employee");
        return R.success("推出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpServletRequest request){
        //设置初始密码123456，需要进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        //获取当前员工id
//        long currentEmpId = (long)request.getSession().getAttribute("employee");
//        employee.setCreateUser(currentEmpId);
//        employee.setUpdateUser(currentEmpId);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        if (name != null){
            queryWrapper.like(Employee::getName, name);
        }
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }


    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee, HttpServletRequest request){
        Object empId = request.getSession().getAttribute("employee");
//        employee.setUpdateUser((long) empId);
//        employee.setUpdateTime(LocalDateTime.now());
        employeeService.updateById(employee);
        return R.success("员工信息修改成功！");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee == null){
            return R.error("没有查询到此员工");
        }
        return R.success(employee);
    }
}
