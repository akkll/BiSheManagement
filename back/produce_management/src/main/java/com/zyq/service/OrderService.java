package com.zyq.service;

import com.zyq.domain.process.Assign;
import com.zyq.domain.process.Order;
import com.zyq.domain.process.Product;
import com.zyq.domain.vo.AssignMidProduct;
import com.zyq.domain.vo.AssignProduct;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className OrderService
 * @date 2023-02-23 16:32
 */
@Service
@Repository
public interface OrderService {
    List<Order> getOrders();
    List<Order> getOrdersByArrange(Boolean type);//根据unit单位编号，返回所有的未分配的订单与已经分配给这个单位的订单
    int updateOrderArrangeState(int unit, List<Integer> list);
    List<Product> getProduct();//获取全部的成品信息
    int updateArrange(List<Integer> list);//修改订单的开始状况
    List<Assign> getAssignByUnit(int unit);//根据车间号返回所属于该车间号的派工记录
    int insertAssignByOrder(int orderid);//updateArrange()调用，超级管理员使用，在开始订单后添加安装车间的派工记录
    int Unit4CuidanStart(Integer assignid);//安装车间使用，根据自身的派工记录添加焊接车间的派工任务
    int Unit3CuidanStart(Integer assignid);//焊接车间使用，根据自身的派工记录添加冲压和弯管车间的派工记录
    Float Unit12GetMaterialComplete(Integer assignid);//冲压和弯管车间使用，调用后就根据所传的assignid去将对应assign设置为开始状态，并取原料(这里需要车间内部模块的支持)

    int Unit123GiveMidComplete(Integer assignid);//冲压和弯管车间将货物交给焊接车间，两个车间对应的assign完成，另外会查看焊接车间对应的assign所需原料是否全部备齐，备齐了就将焊接车间对应的assign设置为完成状态。此外，焊接车间也可以使用这个方法，但是安装车间由于要与订单表交互，所以不可以使用这个方法。
    int Unit4GiveProComplete(Integer assignid);//安装车间将货物交付给生产部门，将对应的assign设置为完成状态，对应的order状态也设置为完成状态，一个订单就此完成
    List<Assign> getAssignCuidanByUnit(int unit);
    List<AssignProduct> getAssignProductByUnit(int unit);
    List<AssignMidProduct> getAssignMidProductByUnit(int unit);
}
