package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zyq.dao.process.*;
import com.zyq.dao.vo.AssignMidProductMapper;
import com.zyq.dao.vo.AssignProductMapper;
import com.zyq.domain.process.*;
import com.zyq.domain.vo.AssignMidProduct;
import com.zyq.domain.vo.AssignProduct;
import com.zyq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className OrderServiceImpl
 * @date 2023-02-23 16:33
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AssignMapper assignMapper;
    @Autowired
    private MidMaterialMapper midMaterialMapper;
    @Autowired
    private MaterialGetMapper materialGetMapper;
    @Autowired
    private AssignProductMapper assignProductMapper;
    @Autowired
    private AssignMidProductMapper assignMidProductMapper;
    @Autowired
    private UnitProcessMapper unitProcessMapper;
    @Autowired
    private FinishedMapper finishedMapper;
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Order> getOrders() {
        List<Order> orders = orderMapper.selectList(null);
        return orders;
    }
    @Override
    public List<Order> getOrdersByArrange(Boolean type) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::isArrange, type);
        List<Order> orders = orderMapper.selectList(queryWrapper);
        return orders;
    }
    @Override
    public int updateOrderArrangeState(int unit, List<Integer> list) {
        return 0;
    }
    @Override
    public List<Product> getProduct() {
        List<Product> products = productMapper.selectList(null);
        return products;
    }
    public List<Assign> getAssignCuidanByUnit(int unit){
        LambdaQueryWrapper<Assign> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Assign::getType, unit).eq(Assign::getStart, false);
        List<Assign> assigns = assignMapper.selectList(queryWrapper);
        return assigns;
    }
    @Override
    public List<Assign> getAssignByUnit(int unit) {
        LambdaQueryWrapper<Assign> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Assign::getType, unit);
        List<Assign> assigns = assignMapper.selectList(queryWrapper);
        return assigns;
    }
    @Override
    public List<AssignProduct> getAssignProductByUnit(int unit){
        List<AssignProduct> assignProducts = assignProductMapper.getassignProductByUnit(unit);
        return assignProducts;
    }
    @Override
    public List<AssignMidProduct> getAssignMidProductByUnit(int unit){
        List<AssignMidProduct> assignProducts = assignMidProductMapper.getassignProductByUnit(unit);
        return assignProducts;
    }
    //下面三个是催单线
    @Override
    public int updateArrange(List<Integer> list) {
        for (Integer integer : list) {
            int i = insertAssignByOrder(integer);
        }
        LambdaUpdateWrapper<Order> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(Order::isArrange, true)
                .in(Order::getId, list);
        int update = orderMapper.update(null, updateWrapper);
        return update;
    }
    @Override
    public int Unit4CuidanStart(Integer assignid){
        Assign assign = getAssignByAssignID(assignid);
        LambdaUpdateWrapper<Assign> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(Assign::getStart, true)
                .eq(Assign::getId, assignid);
        assignMapper.update(null, updateWrapper);
        int i = insertAssignByUnit4(assign);
        return i;
    }
    @Override
    public int Unit3CuidanStart(Integer assignid){
        Assign assign = getAssignByAssignID(assignid);
        LambdaUpdateWrapper<Assign> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Assign::getStart, true).eq(Assign::getId, assignid);
        int update = assignMapper.update(null, updateWrapper);
        int i = insertAssignByUnit3(assign);
        return i;
    }
    //从这里开始是收货线
    @Override
    public Float Unit12GetMaterialComplete(Integer assignid) {
        Assign assign = getAssignByAssignID(assignid);
        float restNum = getRestNum(assign);
        if(restNum==-1){
            LambdaUpdateWrapper<Assign> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(Assign::getStart, true).set(Assign::getMatecomplete, true).eq(Assign::getId, assign.getId());
            assignMapper.update(null, updateWrapper);
            int i = insertMaterialGetByUnit12(assign);
        }
        return restNum;
    }
    @Override
    public int Unit123GiveMidComplete(Integer assignid){
        Assign assign = getAssignByAssignID(assignid);
        LambdaUpdateWrapper<Assign> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Assign::getComplete, true).eq(Assign::getId, assignid);
        int update = assignMapper.update(null, updateWrapper);
        LambdaQueryWrapper<Assign> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Assign::getId, assign.getPrev());
        //获取到父assign
        Assign assign1 = assignMapper.selectOne(queryWrapper1);
        //看父assign对应的子assign是否全部完成，如果全部完成那么父assign也全部完成
        Long aLong = assignMapper.selectCount(new LambdaQueryWrapper<Assign>().eq(Assign::getPrev, assign1.getId()));
        Long aLong1 = assignMapper.selectCount(new LambdaQueryWrapper<Assign>().eq(Assign::getPrev, assign1.getId()).eq(Assign::getComplete, true));
        if(aLong==aLong1){
            LambdaUpdateWrapper<Assign> updateWrapper1 = new LambdaUpdateWrapper<>();
            updateWrapper1.eq(Assign::getId, assign1.getId()).set(Assign::getMatecomplete, true);
            assignMapper.update(null, updateWrapper1);
            insertUnitProcess(assign1.getType(), assign1.getId());
        }
        return 0;
    }
    @Override
    public int Unit4GiveProComplete(Integer assignid){
        Assign assign = getAssignByAssignID(assignid);
        LambdaUpdateWrapper<Assign> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Assign::getComplete, true).eq(Assign::getId, assignid);
        assignMapper.update(null, updateWrapper);
        LambdaUpdateWrapper<Order> updateWrapper1 = new LambdaUpdateWrapper<>();
        updateWrapper1.eq(Order::getId, assign.getOrderid()).set(Order::isComplete, true).set(Order::getCompletedate, new Date(System.currentTimeMillis()));
        int update1 = orderMapper.update(null, updateWrapper1);
        LambdaQueryWrapper<Assign> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Assign::getId,assignid);
        Assign assign1 = assignMapper.selectOne(queryWrapper);
        LambdaQueryWrapper<Order> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Order::getId,assign1.getOrderid());
        Order order = orderMapper.selectOne(queryWrapper1);
        Finished finished = new Finished();
        finished.setNum(order.getNumber());
        finished.setOrderid(order.getId());
        finished.setProductid(order.getProduceid());
        finished.setDate(new Date(System.currentTimeMillis()));
        finishedMapper.insert(finished);
        return update1;
    }
    @Override
    public int insertAssignByOrder(int orderid) {//安装车间根据订单的开始情况添加派工记录
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Order::getId, orderid)
                .eq(Order::isArrange, false);
        Order order = orderMapper.selectOne(queryWrapper);
        if(order!=null){
            Integer produceid = order.getProduceid();
            Integer number = order.getNumber();
            Date accdate = order.getAccdate();
            Date delivery = order.getDelivery();
            Assign assign = new Assign();
            assign.setMaterialID(produceid);
            assign.setAccdate(accdate);
            assign.setDate(delivery);
            assign.setNumber(number);
            assign.setStart(false);
            assign.setComplete(false);
            assign.setType(4);
            assign.setOrderid(orderid);
            assign.setMatecomplete((false));
            assign.setProcomplete(false);
            int insert = assignMapper.insert(assign);
            return insert;
        }else
            return 0;
    }
    public int insertAssignByUnit4(Assign assign){//根据安装车间的派工情况，给焊接车间发催单
        Assign assign1 = new Assign();
        assign1.setOrderid(assign.getOrderid());
        assign1.setStart(false);
        assign1.setComplete(false);
        assign1.setNumber(assign.getNumber());
        assign1.setType(3);
        assign1.setPrev(assign.getId());
        assign1.setDate(assign.getDate());
        assign1.setAccdate(assign.getAccdate());
        assign1.setMaterialID(assign.getMaterialID());
        assign1.setMatecomplete(false);
        assign1.setProcomplete(false);
        int insert = assignMapper.insert(assign1);
        return insert;
    }
    public int insertAssignByUnit3(Assign assign){//根据焊接车间的派工情况给弯管和冲压车间发催单
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Product::getId,assign.getMaterialID());
        Product product = productMapper.selectOne(queryWrapper);
        MidMaterial midMaterial;
        Assign assign1 = copyAssign(assign);
        for (String s : product.getMid().split(",")) {
            Integer integer = Integer.valueOf(s);
            LambdaQueryWrapper<MidMaterial> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(MidMaterial::getId, integer);
            midMaterial = midMaterialMapper.selectOne(queryWrapper1);
            assign1.setId(null);
            assign1.setType(midMaterial.getDepartment());
            assign1.setStart(false);
            assign1.setPrev(assign.getId());
            assign1.setComplete(false);
            assign1.setMatecomplete(false);
            assign1.setMaterialID(integer);
            assign1.setProcomplete(false);
            assign1.setNumber(midMaterial.getMatenum()*assign.getNumber());
            int insert = assignMapper.insert(assign1);
        }
        return 1;
    }
    //以下是收货线
    public int insertMaterialGetByUnit12(Assign assign){
        //根据派工单添加从仓库进料的出库记录
        Integer materialID = assign.getMaterialID();

        LambdaQueryWrapper<MidMaterial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MidMaterial::getId, assign.getMaterialID());
        MidMaterial midMaterial = midMaterialMapper.selectOne(wrapper);//获取assign对应的半成品
        LambdaQueryWrapper<Material> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Material::getId,midMaterial.getMate());
        Material material = materialMapper.selectOne(queryWrapper);//根据半成品的原料找原料表对应的记录
        LambdaUpdateWrapper<Material> updateWrapper = new LambdaUpdateWrapper<>();
        System.out.println(material);
        Float rest = material.getNumber();//余额
        float number = assign.getNumber()*midMaterial.getMatenum();//所需的原料数量
        if(rest-number>0){
            LambdaUpdateWrapper<Material> updateWrapper1 = new LambdaUpdateWrapper<>();
            updateWrapper.set(Material::getNumber,rest-number).eq(Material::getId,material.getId());
            materialMapper.update(null, updateWrapper);
            MaterialGet materialGet = new MaterialGet();
            materialGet.setMaterialid(materialID);
            materialGet.setDate(new Date(System.currentTimeMillis()));
            materialGet.setUnit(assign.getType());
            materialGet.setNumber(number);
            materialGet.setEmployeeid(1);
            materialGet.setAssignid(assign.getId());
            materialGetMapper.insert(materialGet);
            //出库后
            insertUnitProcess(assign.getType(), assign.getId());
            return -1;
        } else {
            return (int)(number-rest);
        }
    }
    public float getRestNum(Assign assign){//获取原料余量与取料的数量关系，若领料数量多余原料余量，返回缺少多少，否则返回-1
        LambdaQueryWrapper<MidMaterial> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MidMaterial::getId, assign.getMaterialID());
        MidMaterial midMaterial = midMaterialMapper.selectOne(wrapper);//获取assign对应的半成品
        LambdaQueryWrapper<Material> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Material::getId,midMaterial.getMate());
        Material material = materialMapper.selectOne(queryWrapper);//根据半成品的原料找原料表对应的记录
        LambdaUpdateWrapper<Material> updateWrapper = new LambdaUpdateWrapper<>();
        System.out.println(material);
        Float rest = material.getNumber();//余额
        float number = assign.getNumber()*midMaterial.getMatenum();//所需的原料数量
        return rest-number>0?-1:number-rest;
    }

    public Assign copyAssign(Assign assign){//复制一个assign，但是不复制id
        Assign assign1 = new Assign();
        assign1.setType(assign.getType());
        assign1.setMaterialID(assign.getMaterialID());
        assign1.setAccdate(assign.getAccdate());
        assign1.setDate(assign.getDate());
        assign1.setComplete(assign1.getComplete());
        assign1.setNumber(assign.getNumber());
        assign1.setStart(assign.getStart());
        assign1.setOrderid(assign.getOrderid());
        return assign1;
    }
    public Assign getAssignByAssignID(int assignid){
        LambdaQueryWrapper<Assign> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Assign::getId, assignid);
        Assign assign = assignMapper.selectOne(queryWrapper);
        return assign;
    }
    //根据assignid查询该assign的原料是否已经备齐，若已备齐则更新
    public Boolean getAssignMateComplete(int assignid){
        LambdaQueryWrapper<Assign> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Assign::getPrev, assignid);
        Long aLong1 = assignMapper.selectCount(queryWrapper);
        LambdaQueryWrapper<Assign> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper.eq(Assign::getPrev, assignid).eq(Assign::getComplete, true);
        Long aLong2 = assignMapper.selectCount(queryWrapper);
        if(aLong2==aLong1){
            Assign assign = getAssignByAssignID(assignid);
            assign.setMatecomplete(true);
        }
        return aLong1==aLong2;
    }

    public int insertUnitProcess(int unit, int assignid){
        Assign assign = getAssignByAssignID(assignid);
        UnitProcess unitProcess = new UnitProcess();
        unitProcess.setUnit(unit);
        unitProcess.setAssignid(assignid);
        unitProcess.setAccdate(assign.getAccdate());
        unitProcess.setDate(assign.getDate());
        unitProcess.setStart(false);
        unitProcess.setComplete(false);
        return unitProcessMapper.insert(unitProcess);
    }
}
