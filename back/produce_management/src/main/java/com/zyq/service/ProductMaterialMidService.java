package com.zyq.service;

import com.zyq.domain.process.Material;
import com.zyq.domain.process.MaterialGet;
import com.zyq.domain.process.MidMaterial;
import com.zyq.domain.process.Product;
import com.zyq.domain.vo.AssignProduct;
import com.zyq.domain.vo.MaterialgetMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ProductMaterialMidService
 * @date 2023-02-28 15:12
 */
//主要用于对产品，半成品和原料进行查询
@Service
public interface ProductMaterialMidService {
    List<Product> getProducts();
    List<MidMaterial> getMidMaterial();
    List<Material> getMaterial(int pageno,int pagenum);
    Long getMaterialCount();
    List<AssignProduct> getAssignProduct(int assignid);
    Long getMaterialGetCount();
    List<MaterialGet> getMaterialGet(int pageno,int pagenum);
    List<MaterialgetMaterial> getMaterialgetMaterial(int pageno,int pagenum);
}