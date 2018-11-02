package com.xmcc.service.impl;

import com.xmcc.dao.SysDeptMapper;
import com.xmcc.dto.SysDeptDto;
import com.xmcc.pojo.SysDept;
import com.xmcc.service.SysDeptService;
import com.xmcc.util.BeanValidator;
import com.xmcc.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: lusiwei
 * @Date: 2018/11/2 10:59
 * @Description:
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public void insert(SysDeptDto sysDeptDto) {
        //验证参数
        BeanValidator.check(sysDeptDto);
        //获取parentId
        Integer parentId = sysDeptDto.getParentId();
        //构造这模式把dto数据设置到pojo
        SysDept sysDept = SysDept.builder().name(sysDeptDto.getName())
                .parentId(parentId).seq(sysDeptDto.getSeq())
                .remark(sysDeptDto.getRemark()).build();

        //设置层级
        sysDept.setLevel(LevelUtil.getLevel(getParentLevel(parentId), parentId));
        //设置操作者 TODO:从session 和cookie拿
        sysDept.setOperator("lusiwei");
        //设置操作时间
        sysDept.setOperateTime(new Date());
        //设置操作者ip
        sysDept.setOperateIp("127.0.0.1");
        sysDeptMapper.insertSelective(sysDept);
    }

    /**
     * 获取parentLevel
     * 根据parentId 从数据库查询parentLevel
     */
    public String getParentLevel(Integer parentId) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(parentId);
        if (sysDept == null) {
            return null;
        }
        return sysDept.getLevel();
    }
}
