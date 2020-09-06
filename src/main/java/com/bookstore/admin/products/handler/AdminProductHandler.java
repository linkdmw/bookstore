package com.bookstore.admin.products.handler;

import com.bookstore.admin.products.service.IAdminProductService;
import com.bookstore.commons.beans.Product;
import com.bookstore.commons.beans.ProductList;
import com.bookstore.utils.IdUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IDEA.
 *
 * @Author: 霍梦威
 * @Date: 2020/6/3 17:36
 */
@Controller
@RequestMapping("admin/products")
public class AdminProductHandler {
    @Autowired
    IAdminProductService adminProductService;

    //商品管理——查询所有商品
    @RequestMapping("listProduct")
    public String listProduct(Model model){
        List<Product> products = adminProductService.findAllProduct();
        model.addAttribute("products",products);
        return "/admin/products/list";
    }

    //商品管理——多条件查询
    @RequestMapping("findProductByManyCondition")
    public String findProductByManyCondition(Product product,Double minPrice,Double maxPrice,Model model){
        System.out.println("多查询：  "+product+maxPrice+minPrice);
        List<Product> products = adminProductService.findProductByManyCondition(product,maxPrice,minPrice);
        model.addAttribute("products",products);
        model.addAttribute("product",product);
        model.addAttribute("minPrice",minPrice);
        model.addAttribute("maxPrice",maxPrice);
        return "/admin/products/list";
    }

    //商品管理——添加商品
    @RequestMapping("addProduct")
    public String addProduct(Model model, Product product, MultipartFile upload, HttpSession session) throws IOException {
        //定义文件保存路径
        String path = session.getServletContext().getRealPath("/productImg");
        String path2 = "G:/ideaProjects/homework/bookstore/src/main/webapp/productImg";
        //防止路径不存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        //定义保存文件名
        String filename = IdUtils.getUUID()+"-"+upload.getOriginalFilename();

        //文件类型转换
        upload.transferTo(new File(path,filename));
        //copy到项目路径
        FileUtils.copyFile(new File(path,filename),new File(path2,filename));

        product.setId(IdUtils.getUUID());
        product.setImgurl("/productImg/"+filename);

        adminProductService.addProduct(product);
        return "redirect:/admin/products/listProduct";
    }

    //商品管理——根据ID查找商品
    @RequestMapping("findProductById")
    public String findProductById(String id,Model model){
        Product product = adminProductService.findProductById(id);
        model.addAttribute("p",product);
        return "/admin/products/edit";
    }

    //商品管理——编辑商品
    @RequestMapping("editProduct")
    public String editProduct(Product product,MultipartFile upload,Model model,HttpSession session) throws IOException {
        //判断是否上传了新图片
        if(!upload.isEmpty()){
            //删除原始图片
            String path2 = "G:/ideaProjects/homework/bookstore/src/main/webapp/productImg";
            String oldPath = session.getServletContext().getRealPath("/") + adminProductService.findProductById(product.getId()).getImgurl();
            File targetFile = new File(oldPath);
            if(targetFile.exists()){
                targetFile.delete();
            }

            //保存新的图片
            String newPath = session.getServletContext().getRealPath("/productImg");
            String fileName = IdUtils.getUUID()+'-'+upload.getOriginalFilename();
            upload.transferTo(new File(newPath,fileName));

            FileUtils.copyFile(new File(newPath,fileName),new File(path2,fileName));

            //赋值新路径
            product.setImgurl("/productImg/"+fileName);
        }

        //修改数据库
        adminProductService.modifyProduct(product);
        return "redirect:/admin/products/listProduct";
    }


    //商品管理——删除商品
    @RequestMapping("removeProduct")
    public String removeProduct(String id,HttpSession session,Model model){
        //获取商品图片并删除
        Product product = adminProductService.findProductById(id);
        String path = session.getServletContext().getRealPath("")+product.getImgurl();
        String path2 = "G:/ideaProjects/homework/bookstore/src/main/webapp/productImg";
        System.out.println("删除图片路径：  "+path);
        File file = new File(path);
        File file2 = new File(path2);
        if(file.exists()){
            file.delete();
        }
        if(file2.exists()){
            file2.delete();
        }

        //删除数据库数据
        adminProductService.removeProductById(id);
        return "redirect:/admin/products/listProduct";
    }


    //商品管理——下载销售榜单
    @RequestMapping("download")
    public void download(String year,String month,HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ProductList> productLists = adminProductService.downloadByTime(year,month);
    String filename = year + "年" + month + "月销售榜单";
    String sheetName = month + "月销售榜单";
    String titleName = year + "年" + month + "月销售榜单";
    String[] columnName = {"商品名称","商品销量"};
    String[][] dataList = new String[productLists.size()][2];
        for (int i=0;i<productLists.size();i++){
        dataList[i][0] = productLists.get(i).getName();
        dataList[i][1] = productLists.get(i).getSalNum();
    }

    //创建excel文件
    HSSFWorkbook wb = new HSSFWorkbook();
    //创建excel中的sheet
    HSSFSheet sheet = wb.createSheet(sheetName);
    //创建sheet的第一行
    HSSFRow row1 = sheet.createRow(0);
    //创建第一行的第一个单元格
    HSSFCell cell = row1.createCell(0);
    //合并第一行的第二个单元格
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
    //给第一行第一个合并后的单元格赋值
        cell.setCellValue(titleName);

    //创建第二行
    HSSFRow row = sheet.createRow(1);
        for (int i=0;i<2;i++){
        row.createCell(i).setCellValue(columnName[i]);
    }

    //创建数据行
        for (int i=0;i<dataList.length;i++){
        row = sheet.createRow(i+2);
        for (int j=0;j<2;j++){
            row.createCell(j).setCellValue(dataList[i][j]);
        }
    }

    String fileName = filename + ".xls";
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("content-Disposition","attachment;filename="+codeFileName(request,fileName));
    OutputStream out = response.getOutputStream();
        wb.write(out);
}

    //处理中文乱码
    private String codeFileName(HttpServletRequest request, String fileNames){
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            String[] ieBrowserArray = {"MSIE","Trident","Edge"};
            if (null != agent && Arrays.binarySearch(ieBrowserArray, agent)<=0){// ie或EDGE浏览器
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");
                codedfilename = name;
            }else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }
}
