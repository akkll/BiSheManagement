package com.zyq.controller;

import com.zyq.controller.utils.R;
import com.zyq.dao.process.MaterialGetMapper;
import com.zyq.domain.process.Material;
import com.zyq.domain.process.MaterialGet;
import com.zyq.domain.vo.MaterialgetMaterial;
import com.zyq.service.ProductMaterialMidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className MaterialController
 * @date 2023-03-22 13:23
 */
@RequestMapping("material")
@RestController
public class MaterialController {
    @Autowired
    private ProductMaterialMidService productMaterialMidService;
    @Autowired
    private MaterialGetMapper materialGetMapper;

    @GetMapping("/getMaterial")
    @ResponseBody
    public R getMaterial(int pageno,int pagenum){
        List<Material> material = productMaterialMidService.getMaterial(pageno,pagenum);
        return new R(true,material);
    }

    @GetMapping("/getMaterialTotal")
    @ResponseBody
    public R getMaterialTotal(){
        Long materialCount = productMaterialMidService.getMaterialCount();
        return new R(true,materialCount);
    }

    @GetMapping("/getMaterialGet")
    @ResponseBody
    public R getMaterialGet(int pageno,int pagenum){
        List<MaterialGet> materialGet = productMaterialMidService.getMaterialGet(pageno, pagenum);
        return new R(true,materialGet);
    }
    @GetMapping("/getMaterialGetMaterial")
    @ResponseBody
    public R getMaterialGetMaterial(int pageno,int pagenum){
        List<MaterialgetMaterial> materialgetMaterial = productMaterialMidService.getMaterialgetMaterial(pageno, pagenum);
        return new R(true,materialgetMaterial);
    }

    @GetMapping("/getMaterialGetTotal")
    @ResponseBody
    public R getMaterialGetTotal(){
        Long materialCount = productMaterialMidService.getMaterialGetCount();
        return new R(true,materialCount);
    }

}
