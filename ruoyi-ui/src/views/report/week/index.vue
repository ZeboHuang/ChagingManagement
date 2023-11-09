<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="站点名称" prop="stationName">
        <el-input
          v-model="queryParams.stationName"
          placeholder="请输入站点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="周起始" prop="weekStart">
        <el-date-picker clearable
          v-model="queryParams.weekStart"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择周起始">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="周结束" prop="weekEnd">
        <el-date-picker clearable
          v-model="queryParams.weekEnd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择周结束">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="累计充电量(度)" prop="chargingCapacity">
        <el-input
          v-model="queryParams.chargingCapacity"
          placeholder="请输入累计充电量(度)"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['report:week:add']"
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
          v-hasPermi="['report:week:edit']"
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
          v-hasPermi="['report:week:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['report:week:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="weekList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="周报表ID" align="center" prop="id" />
      <el-table-column label="站点名称" align="center" prop="stationName" />
      <el-table-column label="周起始" align="center" prop="weekStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.weekStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="周结束" align="center" prop="weekEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.weekEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="累计充电量(度)" align="center" prop="chargingCapacity" width="120" />
      <el-table-column label="累计电费(分)" align="center" prop="electoryPayment" width="120"/>
      <el-table-column label="累计服务费(分)" align="center" prop="servePayment" width="120"/>
      <el-table-column label="累计应付款(分)" align="center" prop="duePayment" width="120"/>
      <el-table-column label="实际应付款(分)" align="center" prop="actualPayment" width="120"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width"  fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['report:week:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['report:week:remove']"
          >删除</el-button>
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

    <!-- 添加或修改周报表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="站点名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入站点名称" />
        </el-form-item>
        <el-form-item label="周起始" prop="weekStart">
          <el-date-picker clearable
            v-model="form.weekStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择周起始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="周结束" prop="weekEnd">
          <el-date-picker clearable
            v-model="form.weekEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择周结束">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="累计充电量(度)" prop="chargingCapacity">
          <el-input v-model="form.chargingCapacity" placeholder="请输入累计充电量(度)" />
        </el-form-item>
        <el-form-item label="累计电费(分)" prop="electoryPayment">
          <el-input v-model="form.electoryPayment" placeholder="请输入累计电费(分)" />
        </el-form-item>
        <el-form-item label="累计服务费(分)" prop="servePayment">
          <el-input v-model="form.servePayment" placeholder="请输入累计服务费(分)" />
        </el-form-item>
        <el-form-item label="累计应付款(分)" prop="duePayment">
          <el-input v-model="form.duePayment" placeholder="请输入累计应付款(分)" />
        </el-form-item>
        <el-form-item label="实际应付款(分)" prop="actualPayment">
          <el-input v-model="form.actualPayment" placeholder="请输入实际应付款(分)" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listWeek, getWeek, delWeek, addWeek, updateWeek } from "@/api/report/week";

export default {
  name: "Week",
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
      // 周报表表格数据
      weekList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stationName: null,
        weekStart: null,
        weekEnd: null,
        chargingCapacity: null,
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
    /** 查询周报表列表 */
    getList() {
      this.loading = true;
      listWeek(this.queryParams).then(response => {
        this.weekList = response.rows;
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
        id: null,
        stationName: null,
        weekStart: null,
        weekEnd: null,
        chargingCapacity: null,
        electoryPayment: null,
        servePayment: null,
        duePayment: null,
        actualPayment: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加周报表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWeek(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改周报表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWeek(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWeek(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除周报表编号为"' + ids + '"的数据项？').then(function() {
        return delWeek(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('report/week/export', {
        ...this.queryParams
      }, `week_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
