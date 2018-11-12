package com.lusiwei.dto;

import com.lusiwei.pojo.SysAcl;
import com.lusiwei.pojo.SysUser;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/6 09:21
 * @Description:
 */
@Setter
@Getter
public class PageUtilDto {

    /**
     * 当前页
     */
    @NotNull
    private Integer curPage;
    /**
     * 每页显示多少条
     */
    private Integer pageSize=5;
    /**
     * 总共多少条数据
     */
    private Long totalCount;
    /**
     * 总共多少页
     */
    private Integer pageCount;
    /**
     * 数据
     */
    private List<SysUser> sysUsers;
    private List<SysAcl> sysAcls;

    public Integer getPageCount() {
        return (int) Math.ceil((totalCount+0.0)/pageSize);
    }
}
