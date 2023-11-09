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
      <el-form-item label="交易日期" prop="transDate">
        <el-date-picker clearable
          v-model="queryParams.transDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交易日期">
        </el-date-picker>
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
          v-hasPermi="['report:day:add']"
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
          v-hasPermi="['report:day:edit']"
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
          v-hasPermi="['report:day:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['report:day:export']"
        >导出</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleChart"
          v-hasPermi="['report:day:chart']"
        >日报表概览</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dayList" @selection-change="handleSelectionChange" :default-sort = "{prop: 'transDate', order: 'descending'}">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日报表ID" align="center" prop="id" />
      <el-table-column label="站点名称" align="center" prop="stationName" />
      <el-table-column label="交易日期" align="center" prop="transDate" width="180" sortable>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.transDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="累计充电量(度)" align="center" prop="chargingCapacity" />
      <el-table-column label="累计电费(分)" align="center" prop="electoryPayment" />
      <el-table-column label="累计服务费(分)" align="center" prop="servePayment" />
      <el-table-column label="累计应付款(分)" align="center" prop="duePayment" />
      <el-table-column label="实际应付款(分)" align="center" prop="actualPayment" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width"  fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['report:day:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['report:day:remove']"
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

    <!-- 添加或修改日报表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="站点名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入站点名称" />
        </el-form-item>
        <el-form-item label="交易日期" prop="transDate">
          <el-date-picker clearable
            v-model="form.transDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易日期">
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
import { listDay, getDay, delDay, addDay, updateDay } from "@/api/report/day";

export default {
  name: "Day",
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
      // 日报表表格数据
      dayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stationName: null,
        transDate: null,
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
    /** 查询日报表列表 */
    getList() {
      this.loading = true;
      listDay(this.queryParams).then(response => {
        this.dayList = response.rows;
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
        transDate: null,
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
      this.title = "添加日报表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDay(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改日报表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDay(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDay(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除日报表编号为"' + ids + '"的数据项？').then(function() {
        return delDay(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('report/day/export', {
        ...this.queryParams
      }, `day_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
