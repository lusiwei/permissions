package com.xmcc.service.impl;

import com.xmcc.dao.SysDeptMapper;
import com.xmcc.dto.DeptLevelDto;
import com.xmcc.pojo.SysDept;
import com.xmcc.service.GetDeptTreeService;
import com.xmcc.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 21:56
 * @Description:
 */
@Service
public class GetDeptTreeServiceImpl implements GetDeptTreeService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<DeptLevelDto> getDeptTree() {
        //获取所有部门
        List<SysDept> sysDepts = sysDeptMapper.queryAllSysDept();
        //遍历部门列表,把部门信息复制到dto对象上,获得一个dto对象列表
        //定义一个空的dtoList来装复制后的dto对象
        List<DeptLevelDto> dtoList = new ArrayList<>();
        for (SysDept sysDept : sysDepts) {
            DeptLevelDto deptLevelDto = DeptLevelDto.copySysDept(sysDept);
            dtoList.add(deptLevelDto);
        }
        //调用一个方法把dtoList 转换成树形结构
        //TODO:
        List<DeptLevelDto> dtoTreeObject = dtoList2Tree(dtoList);

        return dtoTreeObject;
    }

    /**
     * 把List 结构的dto 转换成树形结构
     *
     * @param dtoList
     * @return
     */
    private List<DeptLevelDto> dtoList2Tree(List<DeptLevelDto> dtoList) {
        if (dtoList == null || dtoList.size() == 0) {
            return new ArrayList<>();
        }
        //定义一个List把根节点装起来,即层级最高的部门
        List<DeptLevelDto> rootDeptList = new ArrayList<>();
        //遍历dtoList,把层级最高的部门对象放到rootDeptList中
        for (DeptLevelDto deptLevelDto : dtoList) {
            //如果层级等于LevelUtils中定义的最高层级常量ROOT_LEVEL
            if (LevelUtil.ROOT_LEVEL.equals(deptLevelDto.getLevel())) {
                rootDeptList.add(deptLevelDto);
            }
        }
        //排序
        sort(rootDeptList);
        //拿到rootList之后,遍历每个根dto对象,每个对象定义一个list把子部门放进去
        assignValue(rootDeptList, dtoList);
        return rootDeptList;
    }

    /**
     * 递归遍历rootDeptList,并把子部门赋值进去
     */
    private void assignValue(List<DeptLevelDto> rootDeptList, List<DeptLevelDto> dtoList) {
        for (DeptLevelDto deptLevelDto : rootDeptList) {
            List<DeptLevelDto> levelDtos = new ArrayList<>();
            for (DeptLevelDto levelDto : dtoList) {
                //如果所有部门中parentId 等于 rootList 的ID ,就说明是他的子部门
                if (levelDto.getParentId().equals(deptLevelDto.getId())) {
                    levelDtos.add(levelDto);
                }
            }
            deptLevelDto.setDeptLevelDtoList(levelDtos);
            if (!CollectionUtils.isEmpty(levelDtos)) {
                //排序
                sort(levelDtos);
                //递归
                assignValue(levelDtos, dtoList);
            }
        }

    }

    /**
     * 通过比较器排序
     */
    public void sort(List<DeptLevelDto> dtoList) {
        Collections.sort(dtoList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
    }
}
