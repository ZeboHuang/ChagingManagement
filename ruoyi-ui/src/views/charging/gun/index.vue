<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="150px">
      <el-form-item label="充电枪id" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入充电枪id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充电枪名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入充电枪名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省份" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入省份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入城市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区/县" prop="country">
        <el-input
          v-model="queryParams.country"
          placeholder="请输入区/县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充电站名称" prop="stationName">
        <el-input
          v-model="queryParams.stationName"
          placeholder="请输入充电站名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站分区名称" prop="partitionName">
        <el-input
          v-model="queryParams.partitionName"
          placeholder="请输入站分区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充电桩名称" prop="pileName">
        <el-input
          v-model="queryParams.pileName"
          placeholder="请输入充电桩名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充电桩类型/直流/交流" prop="pileType">
        <el-select v-model="queryParams.pileType" placeholder="请选择充电桩类型/直流/交流" clearable>
          <el-option
            v-for="dict in dict.type.pile_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="枪状态/在线/离网" prop="gunStatus">
        <el-select v-model="queryParams.gunStatus" placeholder="请选择枪状态/在线/离网" clearable>
          <el-option
            v-for="dict in dict.type.gun_status"
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
          v-hasPermi="['charging:gun:add']"
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
          v-hasPermi="['charging:gun:edit']"
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
          v-hasPermi="['charging:gun:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['charging:gun:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gunList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="充电枪id" align="center" prop="id" />
      <el-table-column label="充电枪名称" align="center" prop="name" width="100"/>
      <el-table-column label="对应车位号" align="center" prop="parkingNumber" width="100"/>
      <el-table-column label="省份" align="center" prop="province" />
      <el-table-column label="城市" align="center" prop="city" />
      <el-table-column label="区/县" align="center" prop="country" />
      <el-table-column label="充电站名称" align="center" prop="stationName" width="100"/>
      <el-table-column label="站分区名称" align="center" prop="partitionName" width="100"/>
      <el-table-column label="充电桩id" align="center" prop="pileId" />
      <el-table-column label="充电桩名称" align="center" prop="pileName" width="100"/>
      <el-table-column label="充电桩序列号" align="center" prop="pileNumber" width="100"/>
      <el-table-column label="充电桩类型" align="center" prop="pileType" width="100">
        <template slot-scope="scope">
          <el-tag :type="tagStyle(scope.row.pileType)">
          <dict-tag :options="dict.type.pile_type" :value="scope.row.pileType"/>
        </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="运营商名称" align="center" prop="operator" width="100"/>
      <el-table-column label="枪状态" align="center" prop="gunStatus">
        <template slot-scope="scope">
          <el-tag :type="tagStyle1(scope.row.gunStatus)">
          <dict-tag :options="dict.type.gun_status" :value="scope.row.gunStatus"/>
        </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="生产商" align="center" prop="producer" />
      <el-table-column label="二维码url" align="center" prop="url" :show-overflow-tooltip="true"/>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['charging:gun:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['charging:gun:remove']"
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

    <!-- 添加或修改充电枪对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="960px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
       
        <!-- 第一行 -->
        <el-row>
           <el-col :span="8">
            <el-form-item label="充电枪名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入充电枪名称" />
            </el-form-item>
           </el-col>
           
           <el-col :span="8">
            <el-form-item label="对应车位号" prop="parkingNumber">
              <el-input v-model="form.parkingNumber" placeholder="请输入对应车位号" />
            </el-form-item>
           </el-col>
            
           <el-col :span="8">
            <el-form-item label="省份" prop="province">
              <el-input v-model="form.province" placeholder="请输入省份" />
            </el-form-item>
           </el-col>
        </el-row>

        <!-- 第二行 -->
        <el-row>
          <el-col :span="8">
            <el-form-item label="城市" prop="city">
              <el-input v-model="form.city" placeholder="请输入城市" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="区/县" prop="country">
              <el-input v-model="form.country" placeholder="请输入区/县" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="充电站名称" prop="stationName">
              <el-input v-model="form.stationName" placeholder="请输入充电站名称" />
            </el-form-item>
          </el-col>

        </el-row>

        <!-- 第三行 -->
        <el-row>

          <el-col :span="8">
            <el-form-item label="站分区名称" prop="partitionName">
               <el-input v-model="form.partitionName" placeholder="请输入站分区名称" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="充电桩id" prop="pileId">
              <el-input v-model="form.pileId" placeholder="请输入充电桩id" />
             </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="充电桩名称" prop="pileName">
              <el-input v-model="form.pileName" placeholder="请输入充电桩名称" />
            </el-form-item>
          </el-col>

        </el-row>
        
        <!--第四行  -->
        <el-row>

          <el-col :span="8">
            <el-form-item label="充电桩序列号" prop="pileNumber">
              <el-input v-model="form.pileNumber" placeholder="请输入充电桩序列号" />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="充电桩类型" prop="pileType">
               <el-select v-model="form.pileType" placeholder="请选择充电桩类型/直流/交流">
                <el-option
                  v-for="dict in dict.type.pile_type"
                 :key="dict.value"
                 :label="dict.label"
                 :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="运营商名称" prop="operator">
             <el-input v-model="form.operator" placeholder="请输入运营商名称" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第五行 -->
        <el-row>
          <el-col span="12">
            <el-form-item label="枪状态" prop="gunStatus">
              <el-select v-model="form.gunStatus" placeholder="请选择枪状态/在线/离网">
                <el-option
                  v-for="dict in dict.type.gun_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>

          </el-col>

          <el-col span="12">
            <el-form-item label="生产商" prop="producer">
              <el-input v-model="form.producer" placeholder="请输入生产商" />
            </el-form-item>

          </el-col>
        </el-row>
       
        <!-- 第六行 -->
        <el-row>

          <el-col :span="24">
            <el-form-item label="二维码url" prop="url">
              <el-input v-model="form.url" placeholder="请输入二维码url" />
            </el-form-item>
          </el-col>

        </el-row>

        <!-- 第七行 -->
        <el-row>

          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
             <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>

        </el-row>
     


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGun, getGun, delGun, addGun, updateGun } from "@/api/charging/gun";

export default {
  name: "Gun",
  dicts: ['pile_type', 'gun_status'],
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
      // 充电枪表格数据
      gunList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        name: null,
        province: null,
        city: null,
        country: null,
        stationName: null,
        partitionName: null,
        pileName: null,
        pileType: null,
        gunStatus: null,
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
    /** 查询充电枪列表 */
    getList() {
      this.loading = true;
      listGun(this.queryParams).then(response => {
        this.gunList = response.rows;
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
        name: null,
        parkingNumber: null,
        province: null,
        city: null,
        country: null,
        stationName: null,
        partitionName: null,
        pileId: null,
        pileName: null,
        pileNumber: null,
        pileType: null,
        operator: null,
        gunStatus: null,
        producer: null,
        url: null,
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
      this.title = "添加充电枪";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGun(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改充电枪";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateGun(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGun(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除充电枪编号为"' + ids + '"的数据项？').then(function() {
        return delGun(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('charging/gun/export', {
        ...this.queryParams
      }, `gun_${new Date().getTime()}.xlsx`)
    },
    /** 直流交流颜色 */
    tagStyle(val){
      if(val =='1')
          return "success"
      else if(val == '2')
          return  "warning"
    },
    /** 正常和停用 */
    tagStyle1(val){
      if(val =='1')
          return ""
      else if(val == '2')
          return  "danger"
    },
  }
};
</script>
