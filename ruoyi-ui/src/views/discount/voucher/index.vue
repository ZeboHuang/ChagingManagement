<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="优惠券名称" prop="discountName">
        <el-input
          v-model="queryParams.discountName"
          placeholder="请输入优惠券名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券类型" prop="discountType">
        <el-select v-model="queryParams.discountType" placeholder="请选择优惠券类型" clearable>
          <el-option
            v-for="dict in dict.type.discount_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="优惠券额度" prop="discountQuota">
        <el-input
          v-model="queryParams.discountQuota"
          placeholder="请输入优惠券额度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券满减额度" prop="discountBeginQuota">
        <el-input
          v-model="queryParams.discountBeginQuota"
          placeholder="请输入优惠券满减额度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券有效开始时间" prop="discountEfficientStartTime">
        <el-date-picker clearable
          v-model="queryParams.discountEfficientStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择优惠券有效开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优惠券有效结束时间" prop="discountEfficientEndTime">
        <el-date-picker clearable
          v-model="queryParams.discountEfficientEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择优惠券有效结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="有效天数" prop="discountDays">
        <el-input
          v-model="queryParams.discountDays"
          placeholder="请输入有效天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券数量" prop="discountNumber">
        <el-input
          v-model="queryParams.discountNumber"
          placeholder="请输入优惠券数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券状态" prop="discountStatus">
        <el-select v-model="queryParams.discountStatus" placeholder="请选择优惠券状态" clearable>
          <el-option
            v-for="dict in dict.type.discount_status"
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
          v-hasPermi="['discount:voucher:add']"
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
          v-hasPermi="['discount:voucher:edit']"
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
          v-hasPermi="['discount:voucher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['discount:voucher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="voucherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="优惠券id" align="center" prop="discountId" :show-overflow-tooltip="true"/>
      <el-table-column label="优惠券名称" align="center" prop="discountName" :show-overflow-tooltip="true" />
      <el-table-column label="优惠券类型" align="center" prop="discountType">
        <template slot-scope="scope">
          <el-tag :type="tagStyle(scope.row.discountType)">
          <dict-tag :options="dict.type.discount_type" :value="scope.row.discountType"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="优惠券额度" align="center" prop="discountQuota" />
      <el-table-column label="优惠券满减额度" align="center" prop="discountBeginQuota" />
      <el-table-column label="优惠券有效开始时间" align="center" prop="discountEfficientStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.discountEfficientStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优惠券有效结束时间" align="center" prop="discountEfficientEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.discountEfficientEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="有效天数" align="center" prop="discountDays" />
      <el-table-column label="优惠券数量" align="center" prop="discountNumber" />
      <el-table-column label="优惠券状态" align="center" prop="discountStatus">
        <template slot-scope="scope">
          <el-tag :type="tagStyle(scope.row.discountStatus)">
          <dict-tag :options="dict.type.discount_status" :value="scope.row.discountStatus"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['discount:voucher:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['discount:voucher:remove']"
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

    <!-- 添加或修改优惠券对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="优惠券名称" prop="discountName">
          <el-input v-model="form.discountName" placeholder="请输入优惠券名称" />
        </el-form-item>
        <el-form-item label="优惠券类型" prop="discountType">
          <el-select v-model="form.discountType" placeholder="请选择优惠券类型">
            <el-option
              v-for="dict in dict.type.discount_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="优惠券额度" prop="discountQuota">
          <el-input v-model="form.discountQuota" placeholder="请输入优惠券额度" />
        </el-form-item>
        <el-form-item label="优惠券满减额度" prop="discountBeginQuota">
          <el-input v-model="form.discountBeginQuota" placeholder="请输入优惠券满减额度" />
        </el-form-item>
        <el-form-item label="优惠券有效开始时间" prop="discountEfficientStartTime">
          <el-date-picker clearable
            v-model="form.discountEfficientStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择优惠券有效开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优惠券有效结束时间" prop="discountEfficientEndTime">
          <el-date-picker clearable
            v-model="form.discountEfficientEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择优惠券有效结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="有效天数" prop="discountDays">
          <el-input v-model="form.discountDays" placeholder="请输入有效天数" />
        </el-form-item>
        <el-form-item label="优惠券数量" prop="discountNumber">
          <el-input v-model="form.discountNumber" placeholder="请输入优惠券数量" />
        </el-form-item>
        <el-form-item label="优惠券状态" prop="discountStatus">
          <el-select v-model="form.discountStatus" placeholder="请选择优惠券状态">
            <el-option
              v-for="dict in dict.type.discount_status"
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
    <!-- 查看优惠券详细信息抽屉 -->
    <el-drawer title="" :visible.sync="drawerOpen" direction="ltr" size = "28%">
       <el-descriptions class="margin-top" title="用户详细信息" :column="1" :size="size" :style="{'margin-left': '20px' , 'margin-right':'5px'}">
          <el-descriptions-item>
            <template slot="label">
              优惠券编号
            </template>
            {{form.discountId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券名称
            </template>
            {{form.discountName}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券类型
            </template>
            {{form.discountType}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券额度
            </template>
            {{form.discountQuota}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券满减额度
            </template>
            {{form.discountBeginQuota}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券有效开始时间
            </template>
            {{form.discountEfficientStartTime}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券有效结束时间
            </template>
            {{form.discountEfficientEndTime}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              有效天数
            </template>
            {{form.discountDays}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券数量
            </template>
            {{form.discountNumber}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              优惠券状态
            </template>
            {{form.discountStatus}}
          </el-descriptions-item>
          </el-descriptions>
    </el-drawer>
  </div>
</template>

<script>
import { listVoucher, getVoucher, delVoucher, addVoucher, updateVoucher } from "@/api/discount/voucher";

export default {
  name: "Voucher",
  dicts: ['discount_type', 'discount_status'],
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
      // 优惠券表格数据
      voucherList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        discountName: null,
        discountType: null,
        discountQuota: null,
        discountBeginQuota: null,
        discountEfficientStartTime: null,
        discountEfficientEndTime: null,
        discountDays: null,
        discountNumber: null,
        discountStatus: null
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
    /** 查询优惠券列表 */
    getList() {
      this.loading = true;
      listVoucher(this.queryParams).then(response => {
        this.voucherList = response.rows;
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
        discountId: null,
        discountName: null,
        discountType: null,
        discountQuota: null,
        discountBeginQuota: null,
        discountEfficientStartTime: null,
        discountEfficientEndTime: null,
        discountDays: null,
        discountNumber: null,
        discountStatus: null
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
      this.ids = selection.map(item => item.discountId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加优惠券";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const discountId = row.discountId || this.ids
      getVoucher(discountId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改优惠券";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.discountId != null) {
            updateVoucher(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVoucher(this.form).then(response => {
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
      const discountIds = row.discountId || this.ids;
      this.$modal.confirm('是否确认删除优惠券编号为"' + discountIds + '"的数据项？').then(function() {
        return delVoucher(discountIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('discount/voucher/export', {
        ...this.queryParams
      }, `voucher_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
