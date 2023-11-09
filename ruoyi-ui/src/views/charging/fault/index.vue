<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="故障编码" prop="faultId">
        <el-input v-model="queryParams.faultId" placeholder="请输入故障编码" clearable @keyup.enter.native="handleQuery" />
        <span slot="label">
          <el-tooltip content="故障编码是系统自动生成的编码,例如:5" placement="top">
            <i class="el-icon-question"></i>
          </el-tooltip>
          故障编码
        </span>

      </el-form-item>
      <el-form-item label="故障原因" prop="reason">
        <el-select v-model="queryParams.reason" placeholder="请选择故障原因" clearable>
          <el-option v-for="dict in dict.type.fault_reason" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="充电枪编码" prop="gunId">
        <el-input v-model="queryParams.gunId" placeholder="请输入充电枪编码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="充电桩编码" prop="pileId">
        <el-input v-model="queryParams.pileId" placeholder="请输入充电桩编码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="充电站编码" prop="stationId">
        <el-input v-model="queryParams.stationId" placeholder="请输入充电站编码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input v-model="queryParams.address" placeholder="请输入详细地址" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="反馈用户编码" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入反馈用户编码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="联系人" prop="usre">
        <el-input v-model="queryParams.usre" placeholder="请输入联系人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="联系手机号" prop="phone">
        <el-input v-model="queryParams.phone" placeholder="请输入联系手机号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="联系邮箱" prop="emali">
        <el-input v-model="queryParams.emali" placeholder="请输入联系邮箱" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input v-model="queryParams.createBy" placeholder="请输入创建者" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="统计时间" prop="createTime">
        <el-date-picker clearable v-model="queryParams.createTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择统计时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新者" prop="updateBy">
        <el-input v-model="queryParams.updateBy" placeholder="请输入更新者" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable v-model="queryParams.updateTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="queryParams.remark" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['charging:fault:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['charging:fault:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['charging:fault:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['charging:fault:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="faultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="故障编码" align="center" prop="faultId" />
      <el-table-column label="故障原因" align="center" prop="reason" show-overflow-tooltip="true" width="120">
        <template slot-scope="scope">
          <el-tag :type="reasonStyle(scope.row.reason)">
            <dict-tag :options="dict.type.fault_reason" :value="scope.row.reason" />
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="充电枪编码" align="center" prop="gunId" />
      <el-table-column label="充电桩编码" align="center" prop="pileId" />
      <el-table-column label="充电站编码" align="center" prop="stationId" />
      <el-table-column label="详细地址" align="center" prop="address" show-overflow-tooltip="true" />
      <el-table-column label="反馈用户编码" align="center" prop="userId" />
      <el-table-column label="联系人" align="center" prop="usre" />
      <el-table-column label="联系手机号" align="center" prop="phone" />
      <el-table-column label="联系邮箱" align="center" prop="emali" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="统计时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="180">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['charging:fault:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['charging:fault:remove']">删除</el-button>

          <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)"
            v-hasPermi="['basic:house:detail']">详情</el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改故障管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="故障原因" prop="reason">
          <el-select v-model="form.reason" placeholder="请选择故障原因">
            <el-option v-for="dict in dict.type.fault_reason" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="充电枪编码" prop="gunId">
          <el-input v-model="form.gunId" placeholder="请输入充电枪编码" />
        </el-form-item>
        <el-form-item label="充电桩编码" prop="pileId">
          <el-input v-model="form.pileId" placeholder="请输入充电桩编码" />
        </el-form-item>
        <el-form-item label="充电站编码" prop="stationId">
          <el-input v-model="form.stationId" placeholder="请输入充电站编码" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="反馈用户编码" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入反馈用户编码" />
        </el-form-item>
        <el-form-item label="联系人" prop="usre">
          <el-input v-model="form.usre" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系手机号" />
        </el-form-item>
        <el-form-item label="联系邮箱" prop="emali">
          <el-input v-model="form.emali" placeholder="请输入联系邮箱" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 查看故障管理信息对话框 -->
        <el-dialog :visible.sync="detailOpen" width="900px" append-to-body :key="detailOpen">
              <el-descriptions class="margin-top" title="故障详情" :column="2" :size="size" border>
                  <el-descriptions-item>
                      <template slot="label">
                          故障编码
                        </template>
                      {{ form.faultId }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          故障原因
                        </template>
                      {{ form.reason }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          充电枪编码
                        </template>
                      {{ form.gunId }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          充电桩编码
                        </template>
                      {{ form.pileId }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          充电站编码
                        </template>
                      {{ form.stationId }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          详细地址
                        </template>
                      {{ form.address }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          反馈用户编码
                        </template>
                      {{ form.userId }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          联系人
                        </template>
                      {{ form.usre }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          联系手机号
                        </template>
                      {{ form.phone }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          联系邮箱
                        </template>
                      {{ form.emali }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                         创建者
                        </template>
                      {{ form.createBy }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          统计时间
                        </template>
                      {{ form.createTime }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          更新者
                        </template>
                      {{ form.updateBy }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          更新时间
                        </template>
                      {{ form.updateTime }}
                    </el-descriptions-item>

                  <el-descriptions-item>
                      <template slot="label">
                          备注
                        </template>
                      {{ form.remark }}
                    </el-descriptions-item>



      </el-descriptions>
          </el-dialog>

  </div>
</template>

<script>
import { listFault, getFault, delFault, addFault, updateFault } from "@/api/charging/fault";

export default {
  name: "Fault",
  dicts: ['fault_reason'],
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
      // 故障管理表格数据
      faultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详细弹出层
      detailOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        faultId: null,
        reason: null,
        gunId: null,
        pileId: null,
        stationId: null,
        address: null,
        userId: null,
        usre: null,
        phone: null,
        emali: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询故障管理列表 */
    getList() {
      this.loading = true;
      listFault(this.queryParams).then(response => {
        this.faultList = response.rows;
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
        faultId: null,
        reason: null,
        gunId: null,
        pileId: null,
        stationId: null,
        address: null,
        userId: null,
        usre: null,
        phone: null,
        emali: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.faultId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加故障管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const faultId = row.faultId || this.ids
      getFault(faultId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改故障管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.faultId != null) {
            updateFault(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFault(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const faultIds = row.faultId || this.ids;
      this.$modal.confirm('是否确认删除故障管理编号为"' + faultIds + '"的数据项？').then(function () {
        return delFault(faultIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },

    /** 详细按钮操作 */
    handleView(row) {
      this.detailOpen = true;
      this.form = row;
    },

    /** 故障原因显示风格 danger   success   warning*/
    reasonStyle(val) {

      if (val == '0') {
        return "success"
      } else if (val == '1') {
        return ""
      } else if (val == '2') {
        return "danger"
      } else if (val == '3') {
        return "warning"
      }
    },


    /** 导出按钮操作 */
    handleExport() {
      this.download('charging/fault/export', {
        ...this.queryParams
      }, `fault_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
