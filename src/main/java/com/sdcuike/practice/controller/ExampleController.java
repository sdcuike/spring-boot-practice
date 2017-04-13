package com.sdcuike.practice.controller;

import com.doctor.beaver.domain.result.ModelResult;
import com.sdcuike.mybatis.pageable.PaginationUtil;
import com.sdcuike.practice.domain.Company;
import com.sdcuike.practice.mapper.CompanyMapper;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map.Entry;

@RestController
@RequestMapping(path = "/example",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ExampleController {
    
    @Resource
    private CompanyMapper companyMapper;
    
    @GetMapping("/")
    public ModelResult<String> home() {
        ModelResult<String> modelResult = new ModelResult<>();
        modelResult.setData("hello world spring boot");
        return modelResult;
    }
    
    @GetMapping("/all_company")
    public List<Company> queryAllCompany() {
        return companyMapper.selectAll();
    }
    
    @ApiOperation(value = "queryCompanys", notes = "获取 公司分页")
    @GetMapping("/all_company/page")
    public ModelResult<List<Company>> queryCompanys(Pageable pageable,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        
        ModelResult<List<Company>> result = new ModelResult<>();
        Page<Company> companies = companyMapper.selectAllPageable(pageable);
        result.setData(companies.getContent());
        
        PaginationUtil.setPaginationHttpHeaders(companies, request, response);
        return result;
    }
    
    @Data
    public static class QueryCompanysResponseDto {
        private List<Company> companies;
        private Pageable pageable;
    }
}
