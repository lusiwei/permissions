<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
    <style>
        h4{
            border-bottom: 1px solid #e5e5e5;
            padding: 15px;
        }
        .modal-header{
            border-bottom: 0px;
        }
    </style>
</head>
<body class="no-skin" youdao="bind" style="background: white">
<input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-5"/>

<div class="page-header">
    <h1>
        权限模块管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            维护权限模块和权限点关系
        </small>
    </h1>
</div>
<div class="main-content-inner">
    <div class="col-sm-3">
        <div class="table-header">
            权限模块列表&nbsp;&nbsp;
            <a class="green" href="#">
                <i class="ace-icon fa fa-plus-circle orange bigger-130 aclModule-add"></i>
            </a>
        </div>
        <div id="aclModuleList">
        </div>
    </div>
    <div class="col-sm-9">
        <div class="col-xs-12">
            <div class="table-header">
                权限点列表&nbsp;&nbsp;
                <a class="green" href="#">
                    <i class="ace-icon fa fa-plus-circle orange bigger-130 acl-add"></i>
                </a>
            </div>
            <div>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="dataTables_length" id="dynamic-table_length"><label>
                                展示
                                <select id="pageSize" name="dynamic-table_length" aria-controls="dynamic-table"
                                        class="form-control input-sm">
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="100">100</option>
                                </select> 条记录 </label>
                            </div>
                        </div>
                    </div>
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer"
                           role="grid"
                           aria-describedby="dynamic-table_info" style="font-size:14px">
                        <thead>
                        <tr role="row">
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                权限名称
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                权限模块
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                类型
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                URL
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                状态
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                顺序
                            </th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label=""></th>
                        </tr>
                        </thead>
                        <tbody id="aclList"></tbody>
                    </table>
                    <div class="row" id="aclPage">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialog-aclModule-form" style="display: none;">
    <form id="aclModuleForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="parentId">上级模块</label></td>
                <td>
                    <select id="parentId" name="parentId" data-placeholder="选择模块" style="width: 200px;"></select>
                    <input type="hidden" name="id" id="aclModuleId"/>
                </td>
            </tr>
            <tr>
                <td><label for="aclModuleName">名称</label></td>
                <td><input type="text" name="name" id="aclModuleName" value=""
                           class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclModuleSeq">顺序</label></td>
                <td><input type="text" name="seq" id="aclModuleSeq" value="1"
                           class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclModuleStatus">状态</label></td>
                <td>
                    <select id="aclModuleStatus" name="status" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                        <option value="2">删除</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclModuleRemark">备注</label></td>
                <td><textarea name="remark" id="aclModuleRemark" class="text ui-widget-content ui-corner-all" rows="3"
                              cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="dialog-acl-form" style="display: none;">
    <form id="aclForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="parentId">所属权限模块</label></td>
                <td>
                    <select id="aclModuleSelectId" name="aclModuleId" data-placeholder="选择权限模块"
                            style="width: 200px;"></select>
                </td>
            </tr>
            <tr>
                <td><label for="aclName">名称</label></td>
                <input type="hidden" name="id" id="aclId"/>
                <td><input type="text" name="name" id="aclName" value="" class="text ui-widget-content ui-corner-all">
                </td>
            </tr>
            <tr>
                <td><label for="aclType">类型</label></td>
                <td>
                    <select id="aclType" name="type" data-placeholder="类型" style="width: 150px;">
                        <option value="1">菜单</option>
                        <option value="2">按钮</option>
                        <option value="3">其他</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclUrl">URL</label></td>
                <td><input type="text" name="url" id="aclUrl" value="1" class="text ui-widget-content ui-corner-all">
                </td>
            </tr>
            <tr>
                <td><label for="aclStatus">状态</label></td>
                <td>
                    <select id="aclStatus" name="status" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclSeq">顺序</label></td>
                <td><input type="text" name="seq" id="aclSeq" value="" class="text ui-widget-content ui-corner-all">
                </td>
            </tr>
            <tr>
                <td><label for="aclRemark">备注</label></td>
                <td><textarea name="remark" id="aclRemark" class="text ui-widget-content ui-corner-all" rows="3"
                              cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    拥有该权限点的角色有：
                </h4>
            </div>
            <div class="modal-body">
                按下 ESC 按钮退出。
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script id="aclModuleListTemplate" type="x-tmpl-mustache">
<ol class="dd-list ">
    {{#aclModuleList}}
        <li class="dd-item dd2-item aclModule-name {{displayClass}}" id="aclModule_{{id}}" href="javascript:void(0)" data-id="{{id}}">
            <div class="dd2-content" style="cursor:pointer;">
            {{name}}
            &nbsp;
            <a class="green {{#showDownAngle}}{{/showDownAngle}}" href="#" data-id="{{id}}" >
                <i class="ace-icon fa fa-angle-double-down bigger-120 sub-aclModule"></i>
            </a>
            <span style="float:right;">
                <a class="green aclModule-edit" href="#" data-id="{{id}}" >
                    <i class="ace-icon fa fa-pencil bigger-100"></i>
                </a>
                &nbsp;
                <a class="red aclModule-delete" href="#" data-id="{{id}}" data-name="{{name}}">
                    <i class="ace-icon fa fa-trash-o bigger-100"></i>
                </a>
            </span>
            </div>
        </li>
    {{/aclModuleList}}
</ol>


</script>

<script id="aclListTemplate" type="x-tmpl-mustache">
{{#aclList}}
<tr role="row" class="acl-name odd" data-id="{{id}}"><!--even -->
    <td><a href="#" class="acl-edit" data-id="{{id}}">{{name}}</a></td>
    <td>{{showAclModuleName}}</td>
    <td>{{showType}}</td>
    <td>{{url}}</td>
    <td>{{#bold}}{{showStatus}}{{/bold}}</td>
    <td>{{seq}}</td>
    <td>
        <div class="hidden-sm hidden-xs action-buttons">
            <a class="green acl-edit" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-pencil bigger-100"></i>
            </a>
            <a class="red acl-role" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-flag bigger-100"></i>
            </a>
        </div>
    </td>
</tr>
{{/aclList}}


</script>

<script type="text/javascript">
    $(function () {
        let aclModuleList; // 存储树形权限模块列表
        let aclModuleMap = {}; // 存储map格式权限模块信息
        let aclMap = {}; // 存储map格式的权限点信息
        let optionStr = "";
        let lastClickAclModuleId = -1;

        let aclModuleListTemplate = $('#aclModuleListTemplate').html();
        Mustache.parse(aclModuleListTemplate);
        let aclListTemplate = $('#aclListTemplate').html();
        Mustache.parse(aclListTemplate);

        loadAclModuleTree();

        function loadAclModuleTree() {
            $.ajax({
                url: "/sys/aclModule/fullTree.json",
                success: function (result) {
                    if (result.result) {
                        aclModuleList = result.data;
                        let rendered = Mustache.render(aclModuleListTemplate, {
                            aclModuleList: result.data,
                            "showDownAngle": function () {
                                return function (text, render) {
                                    return (this.list && this.list.length > 0) ? "" : "hidden";
                                }
                            },
                            "displayClass": function () {
                                return "";
                            }
                        });
                        $("#aclModuleList").html(rendered);
                        recursiveRenderAclModule(result.data);
                        bindAclModuleClick();
                    } else {
                        showMessage("加载权限模块", result.message, false);
                    }
                }
            })
        }

        $(".aclModule-add").click(function () {
            $("#dialog-aclModule-form").dialog({
                model: true,
                title: "新增权限模块",
                open: function (event, ui) {
                    $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                    optionStr = "<option value=\"0\">-</option>";
                    recursiveRenderAclModuleSelect(aclModuleList, 1);
                    $("#aclModuleForm")[0].reset();
                    $("#parentId").html(optionStr);
                },
                buttons: {
                    "添加": function (e) {
                        e.preventDefault();
                        updateAclModule(true, function (data) {
                            $("#dialog-aclModule-form").dialog("close");
                        }, function (data) {
                            showMessage("新增权限模块", data.msg, false);
                        })
                    },
                    "取消": function () {
                        $("#dialog-aclModule-form").dialog("close");
                    }
                }
            });
        });

        function updateAclModule(isCreate, successCallback, failCallback) {
            $.ajax({
                url: isCreate ? "/sys/aclModule/insert.json" : "/sys/aclModule/update.json",
                data: $("#aclModuleForm").serializeArray(),
                type: 'POST',
                success: function (result) {
                    if (result.result) {
                        loadAclModuleTree();
                        if (successCallback) {
                            successCallback(result);
                        }
                    } else {
                        if (failCallback) {
                            failCallback(result);
                        }
                    }
                }
            })
        }

        function updateAcl(isCreate, successCallback, failCallback) {
            $.ajax({
                url: isCreate ? "/sys/acl/insert.json" : "/sys/acl/update.json",
                data: $("#aclForm").serializeArray(),
                type: 'POST',
                success: function (result) {
                    if (result.result) {
                        loadAclList(lastClickAclModuleId);
                        if (successCallback) {
                            successCallback(result);
                        }
                    } else {
                        if (failCallback) {
                            failCallback(result);
                        }
                    }
                }
            })
        }

        function recursiveRenderAclModuleSelect(aclModuleList, level) {
            level = level | 0;
            if (aclModuleList && aclModuleList.length > 0) {
                $(aclModuleList).each(function (i, aclModule) {
                    aclModuleMap[aclModule.id] = aclModule;
                    let blank = "";
                    if (level > 1) {
                        for (let j = 3; j <= level; j++) {
                            blank += "..";
                        }
                        blank += "∟";
                    }
                    optionStr += Mustache.render("<option value='{{id}}'>{{name}}</option>", {
                        id: aclModule.id,
                        name: blank + aclModule.name
                    });
                    if (aclModule.list && aclModule.list.length > 0) {
                        recursiveRenderAclModuleSelect(aclModule.list, level + 1);
                    }
                });
            }
        }

        function recursiveRenderAclModule(aclModuleList) {
            if (aclModuleList && aclModuleList.length > 0) {
                $(aclModuleList).each(function (i, aclModule) {
                    aclModuleMap[aclModule.id] = aclModule;
                    if (aclModule.list && aclModule.list.length > 0) {
                        let rendered = Mustache.render(aclModuleListTemplate, {
                            aclModuleList: aclModule.list,
                            "showDownAngle": function () {
                                return function (text, render) {
                                    return (this.list && this.list.length > 0) ? "" : "hidden";
                                }
                            },
                            "displayClass": function () {
                                return "hidden";
                            }
                        });
                        $("#aclModule_" + aclModule.id).append(rendered);
                        recursiveRenderAclModule(aclModule.list);
                    }
                })
            }
        }

        function bindAclModuleClick() {
            $(".sub-aclModule").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                $(this).parent().parent().parent().children().children(".aclModule-name").toggleClass("hidden");
                if ($(this).is(".fa-angle-double-down")) {
                    $(this).removeClass("fa-angle-double-down").addClass("fa-angle-double-up");
                } else {
                    $(this).removeClass("fa-angle-double-up").addClass("fa-angle-double-down");
                }
            });

            $(".aclModule-name").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                let aclModuleId = $(this).attr("data-id");
                handleAclModuleSelected(aclModuleId);
            });

            $(".aclModule-delete").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                let aclModuleId = $(this).attr("data-id");
                let aclModuleName = $(this).attr("data-name");
                if (confirm("确定要删除权限模块[" + aclModuleName + "]吗?")) {
                    $.ajax({
                        url: "/sys/aclModule/delete.json",
                        data: {
                            id: aclModuleId
                        },
                        success: function (result) {
                            if (result.result) {
                                showMessage("删除权限模块[" + aclModuleName + "]", "操作成功", true);
                                loadAclModuleTree();
                            } else {
                                showMessage("删除权限模块[" + aclModuleName + "]", result.message, false);
                            }
                        }
                    });
                }
            });

            $(".aclModule-edit").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                let aclModuleId = $(this).attr("data-id");
                $("#dialog-aclModule-form").dialog({
                    model: true,
                    title: "编辑权限模块",
                    open: function (event, ui) {
                        $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                        optionStr = "<option value=\"0\">-</option>";
                        recursiveRenderAclModuleSelect(aclModuleList, 1);
                        $("#aclModuleForm")[0].reset();
                        $("#parentId").html(optionStr);
                        $("#aclModuleId").val(aclModuleId);
                        let targetAclModule = aclModuleMap[aclModuleId];
                        if (targetAclModule) {
                            $("#parentId").val(targetAclModule.parentId);
                            $("#aclModuleName").val(targetAclModule.name);
                            $("#aclModuleSeq").val(targetAclModule.seq);
                            $("#aclModuleRemark").val(targetAclModule.remark);
                            $("#aclModuleStatus").val(targetAclModule.status);
                        }
                    },
                    buttons: {
                        "更新": function (e) {
                            e.preventDefault();
                            updateAclModule(false, function (data) {
                                $("#dialog-aclModule-form").dialog("close");
                            }, function (data) {
                                showMessage("编辑权限模块", data.msg, false);
                            })
                        },
                        "取消": function () {
                            $("#dialog-aclModule-form").dialog("close");
                        }
                    }
                });
            });
        }

        function handleAclModuleSelected(aclModuleId) {
            if (lastClickAclModuleId != -1) {
                let lastAclModule = $("#aclModule_" + lastClickAclModuleId + " .dd2-content:first");
                lastAclModule.removeClass("btn-yellow");
                lastAclModule.removeClass("no-hover");
            }
            let currentAclModule = $("#aclModule_" + aclModuleId + " .dd2-content:first");
            currentAclModule.addClass("btn-yellow");
            currentAclModule.addClass("no-hover");
            lastClickAclModuleId = aclModuleId;
            loadAclList(aclModuleId);
        }

        function loadAclList(aclModuleId) {
            let pageSize = $("#pageSize").val();
            let url = "/sys/acl/page.json?aclModuleId=" + aclModuleId;
            let pageNo = $("#aclPage .pageNo").val() || 1;
            $.ajax({
                url: url,
                data: {
                    pageSize: pageSize,
                    pageNo: pageNo
                },
                success: function (result) {
                    renderAclListAndPage(result, url);
                }
            })
        }

        function renderAclListAndPage(result, url) {
            if (result.result) {
                if (result.data.totalCount > 0) {
                    let rendered = Mustache.render(aclListTemplate, {
                        aclList: result.data.pageList,
                        "showAclModuleName": function () {
                            return aclModuleMap[this.aclModuleId].name;
                        },
                        "showStatus": function () {
                            return this.status == 1 ? "有效" : "无效";
                        },
                        "showType": function () {
                            return this.type == 1 ? "菜单" : (this.type == 2 ? "按钮" : "其他");
                        },
                        "bold": function () {
                            return function (text, render) {
                                let status = render(text);
                                if (status == '有效') {
                                    return "<span class='label label-sm label-success'>有效</span>";
                                } else if (status == '无效') {
                                    return "<span class='label label-sm label-warning'>无效</span>";
                                } else {
                                    return "<span class='label'>删除</span>";
                                }
                            }
                        }
                    });
                    $("#aclList").html(rendered);
                    bindAclClick();
                    $.each(result.data.pageList, function (i, acl) {
                        aclMap[acl.id] = acl;
                    })
                } else {
                    $("#aclList").html('');
                }
                let pageSize = $("#pageSize").val();
                let pageNo = $("#aclPage .pageNo").val() || 1;

                renderPage(url, result.data.totalCount, pageNo, pageSize, result.data.totalCount > 0 ? result.data.pageList.length : 0, "aclPage", renderAclListAndPage);
            } else {
                showMessage("获取权限点列表", result.message, false);
            }
        }

        function bindAclClick() {
            $(".acl-role").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                let aclId = $(this).attr("data-id");
                $.ajax({
                    url: "/sys/acl/aclRoles.json",
                    data: {
                        aclId: aclId
                    },
                    success: function (result) {
                        if (result.result) {
                            console.log(result)
                            $("#myModal").modal("show");
                            let roleNameList = result.data;
                            let str = "";
                            for (let i of roleNameList.sysRoleSet) {
                                str += "<li>" + i.name + "</li>";
                            }
                            str = str + "<h4>拥有该权限点的用户有：</h4>"
                            for (let i of roleNameList.sysUserSet) {
                                str += "<li>" + i.username + "</li>";
                            }
                            $("#myModal .modal-body").html(str);
                        } else {
                            showMessage("获取权限点分配的用户和角色", result.message, false);
                        }
                    }
                })
            });
            $(".acl-edit").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                let aclId = $(this).attr("data-id");
                $("#dialog-acl-form").dialog({
                    model: true,
                    title: "编辑权限",
                    open: function (event, ui) {
                        $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                        optionStr = "";
                        recursiveRenderAclModuleSelect(aclModuleList, 1);
                        $("#aclForm")[0].reset();
                        $("#aclModuleSelectId").html(optionStr);
                        let targetAcl = aclMap[aclId];
                        if (targetAcl) {
                            $("#aclId").val(aclId);
                            $("#aclModuleSelectId").val(targetAcl.aclModuleId);
                            $("#aclStatus").val(targetAcl.status);
                            $("#aclType").val(targetAcl.type);
                            $("#aclName").val(targetAcl.name);
                            $("#aclUrl").val(targetAcl.url);
                            $("#aclSeq").val(targetAcl.seq);
                            $("#aclRemark").val(targetAcl.remark);
                        }
                    },
                    buttons: {
                        "更新": function (e) {
                            e.preventDefault();
                            updateAcl(false, function (data) {
                                $("#dialog-acl-form").dialog("close");
                            }, function (data) {
                                showMessage("编辑权限", data.msg, false);
                            })
                        },
                        "取消": function () {
                            $("#dialog-acl-form").dialog("close");
                        }
                    }
                });
            })
        }

        $(".acl-add").click(function () {
            $("#dialog-acl-form").dialog({
                model: true,
                title: "新增权限",
                open: function (event, ui) {
                    $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                    optionStr = "";
                    recursiveRenderAclModuleSelect(aclModuleList, 1);
                    $("#aclForm")[0].reset();
                    $("#aclModuleSelectId").html(optionStr);
                },
                buttons: {
                    "添加": function (e) {
                        e.preventDefault();
                        updateAcl(true, function (data) {
                            $("#dialog-acl-form").dialog("close");
                        }, function (data) {
                            showMessage("新增权限", data.msg, false);
                        })
                    },
                    "取消": function () {
                        $("#dialog-acl-form").dialog("close");
                    }
                }
            });
        })
    })
</script>

</body>
</html>
