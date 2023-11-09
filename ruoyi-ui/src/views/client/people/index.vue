<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户密码" prop="userPassword">
        <el-input
          v-model="queryParams.userPassword"
          placeholder="请输入用户密码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="userSex">
        <el-select v-model="queryParams.userSex" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="出生日期" prop="userBirthday">
        <el-date-picker clearable
          v-model="queryParams.userBirthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="邮箱地址" prop="userEmail">
        <el-input
          v-model="queryParams.userEmail"
          placeholder="请输入邮箱地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="userPhonenumber">
        <el-input
          v-model="queryParams.userPhonenumber"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户类型" prop="userType">
        <el-select v-model="queryParams.userType" placeholder="请选择用户类型" clearable>
          <el-option
            v-for="dict in dict.type.user_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="用户状态" prop="userStatus">
        <el-select v-model="queryParams.userStatus" placeholder="请选择用户状态" clearable>
          <el-option
            v-for="dict in dict.type.user_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['client:people:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['client:people:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['client:people:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['client:people:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="peopleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户编号" align="center" prop="userId" :show-overflow-tooltip="true"/>
      <el-table-column label="用户名" align="center" prop="userName" :show-overflow-tooltip="true"/>
      <el-table-column label="用户密码" align="center" prop="userPassword" :show-overflow-tooltip="true"/>
      <el-table-column label="性别" align="center" prop="userSex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.userSex"/>
        </template>
      </el-table-column>
      <el-table-column label="出生日期" align="center" prop="userBirthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.userBirthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱地址" align="center" prop="userEmail" :show-overflow-tooltip="true"/>
      <el-table-column label="手机号" align="center" prop="userPhonenumber" :show-overflow-tooltip="true"/>
      <el-table-column label="用户类型" align="center" prop="userType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_type" :value="scope.row.userType"/>
        </template>
      </el-table-column>
      <el-table-column label="用户状态" align="center" prop="userStatus">
        <template slot-scope="scope">
          <el-tag :type="tagStyle(scope.row.userStatus)">
          <dict-tag :options="dict.type.user_status" :value="scope.row.userStatus"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['client:people:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['client:people:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDrawer(scope.row)"
            v-hasPermi="['basic:house:detail']"
          >详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改客户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="用户密码" prop="userPassword">
          <el-input v-model="form.userPassword" placeholder="请输入用户密码" />
        </el-form-item>
        <el-form-item label="性别" prop="userSex">
          <el-select v-model="form.userSex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" prop="userBirthday">
          <el-date-picker clearable
            v-model="form.userBirthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="邮箱地址" prop="userEmail">
          <el-input v-model="form.userEmail" placeholder="请输入邮箱地址" />
        </el-form-item>
        <el-form-item label="手机号" prop="userPhonenumber">
          <el-input v-model="form.userPhonenumber" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="用户类型" prop="userType">
          <el-select v-model="form.userType" placeholder="请选择用户类型">
            <el-option
              v-for="dict in dict.type.user_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户状态" prop="userStatus">
          <el-select v-model="form.userStatus" placeholder="请选择用户状态">
            <el-option
              v-for="dict in dict.type.user_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 查看用户详细信息抽屉 -->
    <el-drawer title="" :visible.sync="drawerOpen" direction="ltr" size = "28%">
       <el-descriptions class="margin-top" title="用户详细信息" :column="1" :size="size" :style="{'margin-left': '20px' , 'margin-right':'5px'}">
          <el-descriptions-item>
            <template slot="label">
              用户编号
            </template>
            {{form.userId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              用户名称
            </template>
            {{form.userName}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              性别
            </template>
            {{form.userSex}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              出生日期
            </template>
            {{form.userBirthday}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              邮箱地址
            </template>
            {{form.userEmail}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              手机号
            </template>
            {{form.userPhonenumber}}
          </el-descriptions-item>
          </el-descriptions>
          <el-descriptions-item>
            <template slot="label">
              用户类型
            </template>
            {{form.userType}}
          </el-descriptions-item>
          </el-descriptions>
          <el-descriptions-item>
            <template slot="label">
              用户状态
            </template>
            {{form.userStatus}}
          </el-descriptions-item>
          </el-descriptions>
    </el-drawer>


  </div>
</template>

<script>
import { listPeople, getPeople, delPeople, addPeople, updatePeople } from "@/api/client/people";

export default {
  name: "People",
  dicts: ['user_status', 'sys_user_sex', 'user_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 客户表格数据
      peopleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        userPassword: null,
        userSex: null,
        userBirthday: null,
        userEmail: null,
        userPhonenumber: null,
        userType: null,
        userStatus: null
      },
      // 表单参数
      form: {},
      // 弹出层标题
      title: "",
      // 是否显示新增或者修改弹出层
      open: false,
      // 是否显示详细弹出层
      detailOpen: false,
      // 是否显示抽屉
      drawerOpen: false,
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客户列表 */
    getList() {
      this.loading = true;
      listPeople(this.queryParams).then(response => {
        this.peopleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: null,
        userName: null,
        userPassword: null,
        userSex: null,
        userBirthday: null,
        userEmail: null,
        userPhonenumber: null,
        userType: null,
        userStatus: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      getPeople(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != null) {
            updatePeople(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPeople(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 抽屉按钮操作 */
    handleDrawer(row) {
      this.drawerOpen = true;
      this.form = row;
    },
    tagStyle(val){

        if(val == '0'){
            return "success"
        }else if(val == '1'){
            return "danger"
        }else if(val == '2'){
            return ""
        }else if(val == '3'){
            return "warning"
        }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除客户编号为"' + userIds + '"的数据项？').then(function() {
        return delPeople(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/people/export', {
        ...this.queryParams
      }, `people_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
