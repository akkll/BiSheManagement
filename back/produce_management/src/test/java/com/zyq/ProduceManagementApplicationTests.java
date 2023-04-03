package com.zyq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyq.dao.process.AssignMapper;
import com.zyq.dao.vo.AssignProductMapper;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.employee.EmployeeDepart;
import com.zyq.domain.employee.EmployeeJoin;
import com.zyq.domain.process.Assign;
import com.zyq.domain.process.Order;
import com.zyq.domain.vo.AssignProduct;
import com.zyq.service.EmployeeJoinService;
import com.zyq.service.EmployeeService;
import com.zyq.service.impl.EmployeeDepartServiceImpl;
import com.zyq.service.impl.EmployeeJoinServiceImpl;
import com.zyq.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProduceManagementApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDepartServiceImpl employeeDepartService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private EmployeeJoinServiceImpl employeeJoinService;

    @Autowired
    private AssignMapper assignMapper;

    @Autowired
    private AssignProductMapper assignProductMapper;

    @Test
    public void test1(){
        Employee employee = new Employee();
        Date date = new Date(System.currentTimeMillis());
        employee.setName("abc");
        employee.setWorkdate(date);
        employee.setCard("1145162913921391");
        int i = employeeService.updateEmployee(employee);
        System.out.println(i);
    }
    @Test
    public void test2(){
        EmployeeDepart employeeDepart = new EmployeeDepart();
        employeeDepart.setEmployeeID("l-s3216");
        employeeDepart.setApptime(new Date(System.currentTimeMillis()));
        employeeDepart.setTime(new Date(System.currentTimeMillis()));
        employeeDepart.setState(0);
        employeeDepartService.insertApp(employeeDepart);
    }

    @Test
    public void test3(){
        List<EmployeeDepart> employeeDepart = employeeDepartService.getEmployeeDepart();
        employeeDepart.forEach(System.out::println);
    }

    @Test
    public void test4(){
        employeeDepartService.updateStateByID("L-s8264");
    }

    @Test
    public void test5(){
        List<Order> orders = orderService.getOrders();
        orders.forEach(System.out::println);
    }

    @Test
    public void test6(){
//        List<Order> ordersByUnit = orderService.getOrdersByUnit(1);
//        ordersByUnit.forEach(System.out::println);
    }

    @Test
    public void test7(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        int i = orderService.updateOrderArrangeState(1, list);
        System.out.println(i);
    }

    @Test
    public void test8(){
//        EmployeeJoin employeeJoin = new EmployeeJoin();
//        employeeJoin.setName("朱玉奇");
//        int i = employeeJoinService.insertApp(employeeJoin);
//        System.out.println(i);
        List<EmployeeJoin> employeeJoin = employeeJoinService.getEmployeeJoin();
        employeeJoin.forEach(System.out::println);
    }

    @Test
    public void test9(){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(6);
        orderService.updateArrange(integers);
        orderService.Unit4CuidanStart(1);
        orderService.Unit4CuidanStart(2);
        orderService.Unit3CuidanStart(3);
        orderService.Unit3CuidanStart(4);
    }

    @Test
    public void test10(){
        orderService.Unit4CuidanStart(1);
        orderService.Unit4CuidanStart(2);
    }


    @Test
    public void test12(){
        orderService.Unit3CuidanStart(3);
        orderService.Unit3CuidanStart(4);
    }

    @Test
    public void test13(){
        orderService.Unit12GetMaterialComplete(5);
    }

    @Test void test14(){
        for (int i = 6; i < 10; i++) {
            orderService.Unit123GiveMidComplete(i);
        }
    }

    @Test
    public void test15(){
        List<AssignProduct> assignProducts = assignProductMapper.getassignProductByUnit(4);
        assignProducts.forEach(System.out::println);
    }
}
