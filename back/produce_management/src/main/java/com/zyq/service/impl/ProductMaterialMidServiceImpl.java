package com.zyq.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyq.dao.process.MaterialGetMapper;
import com.zyq.dao.process.MaterialMapper;
import com.zyq.dao.process.MidMaterialMapper;
import com.zyq.dao.process.ProductMapper;
import com.zyq.dao.vo.AssignProductMapper;
import com.zyq.dao.vo.MaterialgetMaterialMapper;
import com.zyq.domain.process.Material;
import com.zyq.domain.process.MaterialGet;
import com.zyq.domain.process.MidMaterial;
import com.zyq.domain.process.Product;
import com.zyq.domain.vo.AssignProduct;
import com.zyq.domain.vo.MaterialgetMaterial;
import com.zyq.service.ProductMaterialMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ProductMaterialMidServiceIpl
 * @date 2023-02-28 15:13
 */
@Service
public class ProductMaterialMidServiceImpl implements ProductMaterialMidService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MidMaterialMapper midMaterialMapper;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private AssignProductMapper assignProductMapper;
    @Autowired
    private MaterialGetMapper materialGetMapper;
    @Autowired
    private MaterialgetMaterialMapper materialgetMaterialMapper;

    @Override
    public List<Product> getProducts() {
        List<Product> products = productMapper.selectList(null);
        return products;
    }

    @Override
    public List<MidMaterial> getMidMaterial() {
        List<MidMaterial> midMaterials = midMaterialMapper.selectList(null);
        return midMaterials;
    }

    @Override
    public List<Material> getMaterial(int pageno, int pagenum) {
        Page<Material> page = new Page<>(pageno,pagenum);
        Page<Material> selectPage = materialMapper.selectPage(page, null);
        return selectPage.getRecords();
    }

    @Override
    public Long getMaterialCount() {
        Long aLong = materialMapper.selectCount(null);
        return aLong;
    }

    @Override
    public List<AssignProduct> getAssignProduct(int assignid) {
        List<AssignProduct> assignProducts = assignProductMapper.getassignProductByAssignid(assignid);
        return assignProducts;
    }

    @Override
    public Long getMaterialGetCount() {
        Long aLong = materialGetMapper.selectCount(null);
        return aLong;
    }

    @Override
    public List<MaterialGet> getMaterialGet(int pageno, int pagenum) {
        Page<MaterialGet> materialGetPage = new Page<>(pageno,pagenum);
        Page<MaterialGet> page = materialGetMapper.selectPage(materialGetPage, null);
        return page.getRecords();
    }

    @Override
    public List<MaterialgetMaterial> getMaterialgetMaterial(int pageno, int pagenum) {
        List<MaterialgetMaterial> materialGetMaterial = materialgetMaterialMapper.getMaterialGetMaterial((pageno - 1) * pagenum, pagenum);
        return materialGetMaterial;
    }


}
