package com.zyq.controller;

import com.zyq.controller.utils.R;
import com.zyq.dao.employee.EmployeeDao;
import com.zyq.domain.employee.Employee;
import com.zyq.domain.employee.EmployeeDepart;
import com.zyq.domain.employee.EmployeeJoin;
import com.zyq.domain.vo.KaoqinEmployee;
import com.zyq.service.EmployeeDepartService;
import com.zyq.service.EmployeeService;
import com.zyq.service.impl.EmployeeJoinServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className TestController
 * @date 2023-01-16 12:25
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDepartService employeeDepartService;

    @Autowired
    private EmployeeJoinServiceImpl employeeJoinService;


    @GetMapping()
    @ResponseBody
    public R getAllEmployee(HttpServletResponse response, int pageno, int pagenum){//根据分页返回员工信息
        List<Employee> employee1 = employeeService.getEmployee(pageno, pagenum);
        R r = new R(true, employee1.toArray());
        return r;
    }

    @GetMapping("/count")
    @ResponseBody
    public R test1(HttpServletResponse response){//获取员工有多少人
        long employeeCount = employeeService.getEmployeeCount();
        R r = new R(true, employeeCount);
//        response.addHeader("Access-Control-Allow-Origin", "*");
        return r;
    }

    @PostMapping("")
    public R test2(@RequestBody Employee emp){//修改员工信息
        int i = employeeService.updateEmployee(emp);
        return new R(true, i);
    }

    @GetMapping("departForm")
    public boolean test3(HttpServletResponse res, String type) throws IOException {//把static下的模板文件发到前端
        String currentDirectory = System.getProperty("user.dir");
        String path = currentDirectory+"\\target\\classes\\static\\"+type+".xlsx";
        System.out.println(path);
        File file = new File(path);
        String fileName = type+".xlsx";
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
        return false;
    }


    @GetMapping("getEmployeeById")
    public R test4(String id){
        Employee employee = employeeService.getEmployeeById(id);
        return new R(true, employee);
    }
    @GetMapping("getEmployeeJoinById")
    public R getEmployeeJoinById(String id){
        EmployeeJoin employeeJoinById = employeeJoinService.getEmployeeJoinById(id);
        return new R(true, employeeJoinById);
    }

    @PostMapping("insertDepart")
    public R test5(@RequestBody EmployeeDepart employeeDepart){
        employeeDepart.setState(0);
        System.out.println(employeeDepart);
        int i = employeeDepartService.insertApp(employeeDepart);
        return new R(true, i);
    }
    @PostMapping("insertJoin")
    public R insertJoin(@RequestBody EmployeeJoin employeeJoin){
        employeeJoin.setState(0);
        System.out.println(employeeJoin);
        int i = employeeJoinService.insertApp(employeeJoin);
        return new R(true, i);
    }



    @GetMapping("getDepart")
    public R test6(){//获取所有没被审核的离职数据
        List<EmployeeDepart> employeeDepart = employeeDepartService.getEmployeeDepart();
        return new R(true, employeeDepart);
    }

    @GetMapping("getJoin")
    public R getjoin(){
        List<EmployeeJoin> employeeJoin = employeeJoinService.getEmployeeJoin();
        return new R(true, employeeJoin);
    }

    @PostMapping("/updateDepartStatePass")
    public R test7(String id){
        int i = employeeDepartService.updateStateByID(id);
        return new R(true, i);
    }
    @PostMapping("/updateDepartStatePassJoin")
    public R test8(String id){
        int i = employeeJoinService.updateStateByID(id);
        return new R(true, i);
    }

    @GetMapping("/getKaoqin")
    public R test9(){
        List<KaoqinEmployee> kaoqinEmployee = employeeService.getKaoqinEmployee();
        return new R(true, kaoqinEmployee);
    }

}
