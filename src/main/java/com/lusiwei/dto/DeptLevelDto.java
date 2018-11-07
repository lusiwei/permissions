package com.lusiwei.dto;

import com.lusiwei.pojo.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 20:57
 * @Description:
 *1. 部门层级传输对象继承pojo类的全部属性
 *2 .把实体(entity)对象的属性通过BeanUtils工具类复制到数据传输对象
 */
@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept {
    /**
     * 把
     */
    List<DeptLevelDto> deptLevelDtoList=new ArrayList<>();

    /**
     * 复制entity对象的属性值
     * @param sysDept
     * @return
     * BeanUtils 是springframwork 提供的工具类
     */
    public static DeptLevelDto copySysDept(SysDept sysDept){
        DeptLevelDto deptLevelDto=new DeptLevelDto();
        BeanUtils.copyProperties(sysDept, deptLevelDto);
        return deptLevelDto;
    }
}
