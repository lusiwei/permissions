package com.lusiwei.service.impl;

import com.google.common.base.Strings;
import com.lusiwei.common.ResultJson;
import com.lusiwei.dao.SysUserMapper;
import com.lusiwei.dto.PageUtilDto;
import com.lusiwei.dto.SysUserDto;
import com.lusiwei.exception.ParamException;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysUserService;
import com.lusiwei.util.BeanValidator;
import com.lusiwei.util.MD5Utils;
import com.lusiwei.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 11:33
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

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
        checkEmail(sysUserDto.getMail(), sysUserDto.getId());
        checkTelephone(sysUserDto.getTelephone(), sysUserDto.getDeptId());

        //  todo:邮箱电话要加密
        SysUser sysUser = SysUser.builder().id(sysUserDto.getId()).mail(sysUserDto.getMail()).telephone(sysUserDto.getTelephone()).deptId(sysUserDto.getDeptId()).remark(sysUserDto.getRemark())
                .status(sysUserDto.getStatus()).username(sysUserDto.getUsername()).build();

        sysUser.setOperator("lusiwei");
        sysUser.setOperateTime(new Date());
        sysUser.setOperateIp("127.0.0.1");
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public ResultJson login(String account, String password) {
        if (Strings.isNullOrEmpty(account) || Strings.isNullOrEmpty(password)) {
            throw new ParamException("用户名或者密码为空");
        }
        String encryptedPassword = MD5Utils.calc(password);
        SysUser sysUser = sysUserMapper.login(account);
        String message;
        if (sysUser == null) {
            message = "用户名错误";
        } else if (!sysUser.getPassword().equals(encryptedPassword)) {
            message = "密码错误";
        } else if (sysUser.getStatus() != 1) {
            if (sysUser.getStatus() == 0) {
                message = "该账户已冻结";
            } else {
                message = "该用户已离职";
            }
        } else {
            return ResultJson.success(sysUser);
        }
        return ResultJson.failed(message);
    }

    @Override
    public PageUtilDto queryAllUser(Integer curPage, Integer pageSize) {
        curPage=curPage==null||curPage<=1?1:curPage;
        pageSize=pageSize==null?5:pageSize;
        PageUtilDto pageUtilDto=new PageUtilDto();
        long totalCount=sysUserMapper.queryCount();
        pageUtilDto.setTotalCount(totalCount);
        pageUtilDto.setCurPage(curPage);
        pageUtilDto.setPageSize(pageSize);
        int limitBegin=(curPage-1)*pageSize;
        List<SysUser> sysUsers = sysUserMapper.queryAllUser(limitBegin, pageSize);
        pageUtilDto.setSysUsers(sysUsers);
        return pageUtilDto;
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

    @Override
    public List<SysUser> queryByDeptId(Integer deptId) {
        return sysUserMapper.queryByDeptId(deptId);
    }
}
