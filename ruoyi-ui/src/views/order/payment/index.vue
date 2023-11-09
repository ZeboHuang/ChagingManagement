<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="用户编码" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入用户编码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="支付订单号" prop="paymentId">
        <el-input v-model="queryParams.paymentId" placeholder="请输入支付订单号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="订单类型" prop="orderType">
        <el-select v-model="queryParams.orderType" placeholder="请选择订单类型" clearable>
          <el-option v-for="dict in dict.type.payment_order_type" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="支付模式" prop="paymentMode">
        <el-select v-model="queryParams.paymentMode" placeholder="请选择支付模式" clearable>
          <el-option v-for="dict in dict.type.payment_mode" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="订单状态" prop="orderStatus">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable>
          <el-option v-for="dict in dict.type.payment_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="支付方式" prop="paymentMethod">
        <el-input v-model="queryParams.paymentMethod" placeholder="请输入支付方式" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="支付方式" prop="paymentMethodTag">
        <el-select v-model="queryParams.paymentMethodTag" placeholder="请选择支付方式" clearable>
          <el-option v-for="dict in dict.type.payment_method_tag" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="支付状态" prop="paymentStatus">
        <el-select v-model="queryParams.paymentStatus" placeholder="请选择支付状态" clearable>
          <el-option v-for="dict in dict.type.payment_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="第三方支付编码" prop="thirdPaymentId">
        <el-input v-model="queryParams.thirdPaymentId" placeholder="请输入第三方支付编码" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['order:payment:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['order:payment:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['order:payment:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['order:payment:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paymentList" @selection-change="handleSelectionChange" style="width: 100%">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">

          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="系统编码">
              <span>{{ props.row.sysId }}</span>
            </el-form-item>
            <el-form-item label="用户编码">
              <span>{{ props.row.userId }}</span>
            </el-form-item>
            <el-form-item label="支付订单号">
              <span>{{ props.row.paymentId }}</span>
            </el-form-item>
            <el-form-item label="订单类型">
              <dict-tag :options="dict.type.payment_order_type" :value="props.row.orderType" />
            </el-form-item>

            <el-form-item label="支付模式">
              <dict-tag :options="dict.type.payment_mode" :value="props.row.paymentMode" />
            </el-form-item>
            <el-form-item label="订单状态">
              <dict-tag :options="dict.type.payment_order_status" :value="props.row.orderStatus" />
            </el-form-item>
            <el-form-item label="支付方式(其他)">
              <span>{{ props.row.paymentMethod }}</span>
            </el-form-item>
            <el-form-item label="支付方式">
              <dict-tag :options="dict.type.payment_method_tag" :value="props.row.paymentMethodTag" />
            </el-form-item>
            <el-form-item label="支付状态">
              <dict-tag :options="dict.type.payment_status" :value="props.row.paymentStatus" />
            </el-form-item>
            <el-form-item label="第三方支付编码">
              <span>{{ props.row.thirdPaymentId }}</span>
            </el-form-item>
            <el-form-item label="备注">
              <span>{{ props.row.remark }}</span>
            </el-form-item>
            <el-form-item label="创建时间"> 
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column label="支付订单号" prop="paymentId">
      </el-table-column>
      <el-table-column label="用户编码" prop="userId">
      </el-table-column>
      <el-table-column label="第三方支付编码" prop="thirdPaymentId">
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime">
      </el-table-column>
    </el-table>









<!-- 
    <el-table v-loading="loading" :data="paymentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系统编码" align="center" prop="sysId" />
      <el-table-column label="用户编码" align="center" prop="userId" />
      <el-table-column label="支付订单号" align="center" prop="paymentId" />
      <el-table-column label="订单类型" align="center" prop="orderType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.payment_order_type" :value="scope.row.orderType" />
        </template>
      </el-table-column>
      <el-table-column label="支付模式" align="center" prop="paymentMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.payment_mode" :value="scope.row.paymentMode" />
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="orderStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.payment_status" :value="scope.row.orderStatus" />
        </template>
      </el-table-column>
      <el-table-column label="支付方式" align="center" prop="paymentMethod" />
      <el-table-column label="支付方式" align="center" prop="paymentMethodTag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.payment_method_tag" :value="scope.row.paymentMethodTag" />
        </template>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="paymentStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.payment_status" :value="scope.row.paymentStatus" />
        </template>
      </el-table-column>
      <el-table-column label="第三方支付编码" align="center" prop="thirdPaymentId" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['order:payment:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['order:payment:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改支付订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户编码" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户编码" />
        </el-form-item>
        <el-form-item label="支付订单号" prop="paymentId">
          <el-input v-model="form.paymentId" placeholder="请输入支付订单号" />
        </el-form-item>
        <el-form-item label="订单类型" prop="orderType">
          <el-select v-model="form.orderType" placeholder="请选择订单类型">
            <el-option v-for="dict in dict.type.payment_order_type" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付模式" prop="paymentMode">
          <el-select v-model="form.paymentMode" placeholder="请选择支付模式">
            <el-option v-for="dict in dict.type.payment_mode" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单状态" prop="orderStatus">
          <el-select v-model="form.orderStatus" placeholder="请选择订单状态">
            <el-option v-for="dict in dict.type.payment_status" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付方式" prop="paymentMethod">
          <el-input v-model="form.paymentMethod" placeholder="请输入支付方式" />
        </el-form-item>
        <el-form-item label="支付方式" prop="paymentMethodTag">
          <el-select v-model="form.paymentMethodTag" placeholder="请选择支付方式">
            <el-option v-for="dict in dict.type.payment_method_tag" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付状态" prop="paymentStatus">
          <el-select v-model="form.paymentStatus" placeholder="请选择支付状态">
            <el-option v-for="dict in dict.type.payment_status" :key="dict.value" :label="dict.label"
              :value="parseInt(dict.value)"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="第三方支付编码" prop="thirdPaymentId">
          <el-input v-model="form.thirdPaymentId" placeholder="请输入第三方支付编码" />
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

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 120px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>


<script>
import { listPayment, getPayment, delPayment, addPayment, updatePayment } from "@/api/order/payment";

export default {
  name: "Payment",
  dicts: ['payment_order_type', 'payment_mode', 'payment_status', 'payment_method_tag','payment_order_status'],
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
      // 支付订单表格数据
      paymentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        paymentId: null,
        orderType: null,
        paymentMode: null,
        orderStatus: null,
        paymentMethod: null,
        paymentMethodTag: null,
        paymentStatus: null,
        thirdPaymentId: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户编码不能为空", trigger: "blur" }
        ],
        paymentId: [
          { required: true, message: "支付订单号不能为空", trigger: "blur" }
        ],
        thirdPaymentId: [
          { required: true, message: "第三方支付编码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询支付订单列表 */
    getList() {
      this.loading = true;
      listPayment(this.queryParams).then(response => {
        this.paymentList = response.rows;
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
        sysId: null,
        userId: null,
        paymentId: null,
        orderType: null,
        paymentMode: null,
        orderStatus: null,
        paymentMethod: null,
        paymentMethodTag: null,
        paymentStatus: null,
        thirdPaymentId: null,
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
      this.ids = selection.map(item => item.sysId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加支付订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sysId = row.sysId || this.ids
      getPayment(sysId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支付订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sysId != null) {
            updatePayment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPayment(this.form).then(response => {
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
      const sysIds = row.sysId || this.ids;
      this.$modal.confirm('是否确认删除支付订单编号为"' + sysIds + '"的数据项？').then(function () {
        return delPayment(sysIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('order/payment/export', {
        ...this.queryParams
      }, `payment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
