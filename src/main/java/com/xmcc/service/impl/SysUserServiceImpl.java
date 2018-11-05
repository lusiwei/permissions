package com.xmcc.service.impl;

import com.xmcc.dao.SysUserMapper;
import com.xmcc.dto.SysUserDto;
import com.xmcc.exception.ParamException;
import com.xmcc.pojo.SysUser;
import com.xmcc.service.SysUserService;
import com.xmcc.util.BeanValidator;
import com.xmcc.util.MD5Utils;
import com.xmcc.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 11:33
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 增加用户
     */
    @Override
    public void insert(SysUserDto sysUserDto) {
        BeanValidator.check(sysUserDto);
        //判断邮箱
        checkEmail(sysUserDto.getMail(), null);
        //判断电话
        checkTelephone(sysUserDto.getTelephone(), null);
        SysUser sysUser = SysUser.builder().username(sysUserDto.getUsername()).telephone(sysUserDto.getTelephone())
                .mail(sysUserDto.getMail()).deptId(sysUserDto.getDeptId()).remark(sysUserDto.getRemark()).status(sysUserDto.getStatus())
                .build();
        String password = MD5Utils.calc(PasswordUtil.getPassword());
        sysUser.setPassword(password);
        sysUser.setOperator("lusiwei");
        sysUser.setOperateTime(new Date());
        sysUser.setOperateIp("127.0.0.1");
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void update(SysUserDto sysUserDto) {
        BeanValidator.check(sysUserDto);
        checkEmail(sysUserDto.getMail(),sysUserDto.getId());
        checkTelephone(sysUserDto.getTelephone(),sysUserDto.getDeptId());

        //  todo:邮箱电话要加密
        SysUser sysUser = SysUser.builder().id(sysUserDto.getId()).mail(sysUserDto.getMail()).telephone(sysUserDto.getTelephone()).deptId(sysUserDto.getDeptId()).remark(sysUserDto.getRemark())
                .status(sysUserDto.getStatus()).username(sysUserDto.getUsername()).build();

        sysUser.setOperator("lusiwei");
        sysUser.setOperateTime(new Date());
        sysUser.setOperateIp("127.0.0.1");
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }


    @Override
    public void checkTelephone(String telephone, Integer id) {
        if (sysUserMapper.checkTelephone(telephone, id) > 0) {
            throw new ParamException("该电话号码已经注册过了");
        }
    }

    @Override
    public void checkEmail(String mail, Integer id) {
        if (sysUserMapper.checkEmail(mail, id) > 0) {
            throw new ParamException("邮箱已经被注册");
        }
    }
}
