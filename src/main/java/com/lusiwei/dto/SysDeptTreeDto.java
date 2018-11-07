package com.lusiwei.dto;

import com.lusiwei.pojo.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 15:20
 * @Description:
 */
@Setter
@Getter
@ToString
public class SysDeptTreeDto extends SysDept {

    /** 子部门List */
    private List<SysDeptTreeDto> list;

    public static SysDeptTreeDto getSysDeptTreeDto(SysDept sysDept) {
        SysDeptTreeDto sysDeptTreeDto=new SysDeptTreeDto();
        BeanUtils.copyProperties(sysDept,sysDeptTreeDto);
        return sysDeptTreeDto;
    }

}
