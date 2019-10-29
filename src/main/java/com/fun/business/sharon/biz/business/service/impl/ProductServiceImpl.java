package com.fun.business.sharon.biz.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.bean.Product;
import com.fun.business.sharon.biz.business.bean.ProductPicInfo;
import com.fun.business.sharon.biz.business.dao.ProductMapper;
import com.fun.business.sharon.biz.business.dao.ProductPicInfoMapper;
import com.fun.business.sharon.biz.business.service.FileInfoService;
import com.fun.business.sharon.biz.business.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.vo.AddProductVo;
import com.fun.business.sharon.biz.business.vo.ProductInfoSearchVo;
import com.fun.business.sharon.common.Const;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.utils.ObjectUtil;
import com.fun.business.sharon.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-07-01
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Value("${sharon.uploadPath}")
    private String uploadPath;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPicInfoMapper productPicInfoMapper;

    @Override
    public IPage<Product> getProductList(ProductInfoSearchVo vo) {
        int pageIndex = vo.getPageIndex();
        int pageSize = vo.getPageSize();
        pageIndex = pageIndex == 0 ? 1 : pageIndex;
        pageSize = pageSize == 0 ? 10 : pageSize;
        int offset = (pageIndex - 1) * pageSize;

        IPage<Product> page = new Page(pageIndex, pageSize);
        int total = productMapper.getProductListCount(vo);
        List<Product> list = productMapper.getProductList(vo, offset, pageSize);

        page.setCurrent(pageIndex);
        page.setRecords(list);
        page.setTotal(total);
        page.setPages(pageSize);
        return page;
    }

    @Override
    @Transactional
    public int addOrEditProduct(AddProductVo productVo) {
        Integer result = 0;
        if (ObjectUtil.isNotEmpty(productVo.getId())) { // 编辑
            log.info(new Date()  + "编辑产品信息：" + JSON.toJSONString(productVo));
            Product product = productMapper.selectById(productVo.getId());
            if (ObjectUtil.isNotEmpty(product)) {
                insertOrUpdateProductInfo(product, productVo);
                result = productMapper.updateById(product);
                // 更新图片表 productPicInfoMapper
                if (ObjectUtil.isNotEmpty(productVo.getFirstImage()) || ObjectUtil.isNotEmpty(productVo.getReferenceImages())) {
                    ProductPicInfo productPicInfo = productPicInfoMapper.selectOne(new QueryWrapper<ProductPicInfo>().eq("product_id", product.getId()));
                    if (ObjectUtil.isNotEmpty(productPicInfo)) {
                        if (ObjectUtil.isNotEmpty(productVo.getFirstImage())) {
                            uploadFile(productVo.getFirstImage(), productVo.getId());
                        }else {

                        }
                    }else {

                    }
                }
            }
        }else { // 新增
            Product product = new Product();
            product.setCreateAt(new Date());
            // 生成系统唯一标识
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            product.setUnitTag(uuid);
            insertOrUpdateProductInfo(product, productVo);
            result = productMapper.insert(product);
            // 插入图片表

        }

        return result;
    }

    @Override
    public Integer delProduct(Integer productId) {
        Product product = productMapper.selectById(productId);
        if (ObjectUtil.isNotEmpty(product)) {
            log.info("产品信息被设置为停售产品：" + product.getName());
            product.setStatus(0);
            return productMapper.updateById(product);
        }
        return 0;
    }

    private void uploadFile(MultipartFile[] file, Integer id) {
        try {
            if (ObjectUtil.isNotEmpty(file)) {
                for (MultipartFile sonFile : file) {
                    String fileType = "";
                    String originalName = sonFile.getOriginalFilename();
                    if (StringUtil.isNotEmpty(originalName)) {
                        String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                        if ("doc".equals(prefix) || "docx".equals(prefix)) {
                            fileType = Const.DOC_FILE;
                        } else if ("xls".equals(prefix) || "xlsx".equals(prefix)) {
                            fileType = Const.EXCEL_FILE;
                        } else {
                            fileType = Const.PIC_FILE;
                        }
                    }
                    String uniquenessName = new Date().getTime() + originalName;
                    String filePath = uploadPath + fileType + uniquenessName;
                    File newFile = new File(filePath);
                    sonFile.transferTo(newFile);

                    // 存入表信息
//                    if (ObjectUtil.isNotEmpty(id)) {
//                        ProductPicInfo productPicInfo = productPicInfoMapper.selectOne(new QueryWrapper<ProductPicInfo>().eq("product_id", id));
//                        productPicInfo.setFirstImage(Const.DOMAIN + "/" + uniquenessName);
//                        productPicInfoMapper.updateById(productPicInfo);
//                    }else {
//                        ProductPicInfo productPicInfo = new ProductPicInfo();
//                        productPicInfo.setFirstImage(Const.DOMAIN + "/" + uniquenessName);
//                        productPicInfo.setProductId(id);
//                        productPicInfoMapper.insert(productPicInfo);
//                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            throw new OperateException("图片上传失败！");
        }


    }

    private void insertOrUpdateProductInfo(Product product, AddProductVo productVo) {
        String applyTo = productVo.getApplyTo();
        String description = productVo.getDescription();
        String material = productVo.getMaterial();
        String model = productVo.getModel();
        String name = productVo.getName();
        if (StringUtil.isNotEmpty(applyTo)) {
            product.setApplyTo(applyTo);
        }
        if (StringUtil.isNotEmpty(description)) {
            product.setDescription(description);
        }
        if (StringUtil.isNotEmpty(material)) {
            product.setMaterial(material);
        }
        if (StringUtil.isNotEmpty(model)) {
            product.setModel(model);
        }
        if (StringUtil.isNotEmpty(name)) {
            product.setName(name);
        }
        product.setUpdateAt(new Date());
    }
}
