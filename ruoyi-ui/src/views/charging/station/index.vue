<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="系统ID" prop="sysId">
        <el-input
          v-model="queryParams.sysId"
          placeholder="请输入系统ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
        
      </el-form-item>
      <el-form-item label="充电站ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入充电站ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
        <span slot="label">
          <el-tooltip content="充电站ID就是充电站的编码，例如：123123" placement="top">
            <i class="el-icon-question"></i>
          </el-tooltip>
          充电站ID
        </span>
      </el-form-item>
      <el-form-item label="站点名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入站点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择站点类型" clearable>
          <el-option
            v-for="dict in dict.type.station_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
      <el-form-item label="详细地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="建设场所" prop="site">
        <el-input
          v-model="queryParams.site"
          placeholder="请输入建设场所"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="可容纳量" prop="capacity">
        <el-input
          v-model="queryParams.capacity"
          placeholder="请输入可容纳量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务折扣费" prop="discount">
        <el-input
          v-model="queryParams.discount"
          placeholder="请输入服务折扣费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否支持优惠券" prop="couponStatus">
        <el-select v-model="queryParams.couponStatus" placeholder="请选择是否支持优惠券" clearable>
          <el-option
            v-for="dict in dict.type.is_or_not"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否免费停车" prop="parkingFreeStatus">
        <el-select v-model="queryParams.parkingFreeStatus" placeholder="请选择是否免费停车" clearable>
          <el-option
            v-for="dict in dict.type.is_or_not"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="站点标签" prop="tag">
        <el-select v-model="queryParams.tag" placeholder="请选择站点标签" clearable>
          <el-option
            v-for="dict in dict.type.station_tag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="运营商名称" prop="operator">
        <el-input
          v-model="queryParams.operator"
          placeholder="请输入运营商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点状态" prop="stationStatus">
        <el-select v-model="queryParams.stationStatus" placeholder="请选择站点状态" clearable>
          <el-option
            v-for="dict in dict.type.charging_station_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分区数量" prop="regionNum">
        <el-input
          v-model="queryParams.regionNum"
          placeholder="请输入分区数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="营业时间(起)" prop="businessTimeStart">
        <el-date-picker clearable
          v-model="queryParams.businessTimeStart"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择营业时间(起)">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="营业时间(止)" prop="businessTimeEnd">
        <el-date-picker clearable
          v-model="queryParams.businessTimeEnd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择营业时间(止)">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="联系人" prop="contact">
        <el-input
          v-model="queryParams.contact"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新者" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input
          v-model="queryParams.remark"
          placeholder="请输入备注"
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
          v-hasPermi="['charging:station:add']"
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
          v-hasPermi="['charging:station:edit']"
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
          v-hasPermi="['charging:station:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['charging:station:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系统ID" align="center" prop="sysId" />
      <el-table-column label="充电站ID" align="center" prop="id" />
      <el-table-column label="站点名称" align="center" prop="name" />
      <el-table-column label="站点类型" align="center" prop="type">
        <template slot-scope="scope">
          <el-tag :type="stationStyle(scope.row.type)">
          <dict-tag :options="dict.type.station_type" :value="scope.row.type"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="省份" align="center" prop="province" />
      <el-table-column label="城市" align="center" prop="city" />
      <el-table-column label="区/县" align="center" prop="country" />
      <el-table-column label="详细地址" align="center" prop="address" :show-overflow-tooltip="true"/>
      <el-table-column label="建设场所" align="center" prop="site" />
      <el-table-column label="可容纳量" align="center" prop="capacity" />
      <el-table-column label="经度" align="center" prop="longitude" />
      <el-table-column label="纬度" align="center" prop="latitude" />
      <el-table-column label="服务折扣费" align="center" prop="discount" />
      <el-table-column label="是否支持优惠券" align="center" prop="couponStatus">
        <template slot-scope="scope">
          <el-tag :type="iscouponStyle(scope.row.couponStatus)">
          <dict-tag :options="dict.type.is_or_not" :value="scope.row.couponStatus"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否免费停车" align="center" prop="parkingFreeStatus">
        <template slot-scope="scope">
          <el-tag :type="isFreeStyle(scope.row.parkingFreeStatus)">
          <dict-tag :options="dict.type.is_or_not" :value="scope.row.parkingFreeStatus"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="站点标签" align="center" prop="tag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.station_tag" :value="scope.row.tag"/>
        </template>
      </el-table-column>
      <el-table-column label="运营商名称" align="center" prop="operator" />
      <el-table-column label="站点状态" align="center" prop="stationStatus">
        <template slot-scope="scope">
        
            <dict-tag :options="dict.type.charging_station_status" :value="scope.row.stationStatus"/>
           
        </template>
      </el-table-column>
      <el-table-column label="分区数量" align="center" prop="regionNum" />
      <el-table-column label="营业时间(起)" align="center" prop="businessTimeStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.businessTimeStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="营业时间(止)" align="center" prop="businessTimeEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.businessTimeEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系人" align="center" prop="contact" />
      <el-table-column label="联系电话" align="center" prop="phoneNumber" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
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
      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['charging:station:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['charging:station:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['charging:station:detail']"
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

    <!-- 添加或修改充电站管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="充电站ID" prop="id">
          <el-input v-model="form.id" placeholder="请输入充电站ID" />
        </el-form-item>
        <el-form-item label="站点名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入站点名称" />
        </el-form-item>
        <el-form-item label="站点类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择站点类型">
            <el-option
              v-for="dict in dict.type.station_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <el-input v-model="form.province" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="区/县" prop="country">
          <el-input v-model="form.country" placeholder="请输入区/县" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="建设场所" prop="site">
          <el-input v-model="form.site" placeholder="请输入建设场所" />
        </el-form-item>
        <el-form-item label="可容纳量" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入可容纳量" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="服务折扣费" prop="discount">
          <el-input v-model="form.discount" placeholder="请输入服务折扣费" />
        </el-form-item>
        <el-form-item label="是否支持优惠券" prop="couponStatus">
          <el-select v-model="form.couponStatus" placeholder="请选择是否支持优惠券">
            <el-option
              v-for="dict in dict.type.is_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否免费停车" prop="parkingFreeStatus">
          <el-select v-model="form.parkingFreeStatus" placeholder="请选择是否免费停车">
            <el-option
              v-for="dict in dict.type.is_or_not"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="站点标签" prop="tag">
          <el-select v-model="form.tag" placeholder="请选择站点标签">
            <el-option
              v-for="dict in dict.type.station_tag"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="运营商名称" prop="operator">
          <el-input v-model="form.operator" placeholder="请输入运营商名称" />
        </el-form-item>
        <el-form-item label="站点状态" prop="stationStatus">
          <el-select v-model="form.stationStatus" placeholder="请选择站点状态">
            <el-option
              v-for="dict in dict.type.charging_station_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分区数量" prop="regionNum">
          <el-input v-model="form.regionNum" placeholder="请输入分区数量" />
        </el-form-item>
        <el-form-item label="营业周期(二进制表示)" prop="businessCycle">
          <el-input v-model="form.businessCycle" placeholder="请输入营业周期(二进制表示)" />
        </el-form-item>
        <el-form-item label="营业时间(起)" prop="businessTimeStart">
          <el-date-picker clearable
            v-model="form.businessTimeStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择营业时间(起)">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="营业时间(止)" prop="businessTimeEnd">
          <el-date-picker clearable
            v-model="form.businessTimeEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择营业时间(止)">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入联系电话" />
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


    <!-- 查看充电站管理信息对话框 -->
    <el-dialog :visible.sync="detailOpen" width="900px" append-to-body :key="detailOpen">
        <el-descriptions class="margin-top" title="充电站详情" :column="2" :size="size" border>
          <el-descriptions-item>
            <template slot="label">
              系统ID
            </template>
            {{form.sysId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              充电站ID
            </template>
            {{form.id}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              站点名称
            </template>
            {{form.name}}
          </el-descriptions-item>
          
          <el-descriptions-item>
            <template slot="label">
              站点类型
            </template>
            {{form.type}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              省份
            </template>
            {{form.province}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              城市
            </template>
            {{form.city}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              区/县
            </template>
            {{form.country}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              详细地址
            </template>
            {{form.address}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              建设场所
            </template>
            {{form.site}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              可容纳量
            </template>
            {{form.capacity}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              经度
            </template>
            {{form.longitude}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              纬度
            </template>
            {{form.latitude}}
          </el-descriptions-item>
          
          <el-descriptions-item>
            <template slot="label">
              服务折扣费
            </template>
            {{form.discount}}
          </el-descriptions-item>
          
          <el-descriptions-item>
            <template slot="label">
              是否支持优惠券
            </template>
            {{form.couponStatus}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              是否免费停车
            </template>
            {{form.parkingFreeStatus}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              站点标签
            </template>
            {{form.tag}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              运营商名称
            </template>
            {{form.operator}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              站点状态
            </template>
            {{form.stationStatus}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              分区数量
            </template>
            {{form.regionNum}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              营业周期(二进制表示)
            </template>
            {{form.businessCycle}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              营业时间(起)
            </template>
            {{form.businessTimeStart}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              营业时间(止)
            </template>
            {{form.businessTimeEnd}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              联系人
            </template>
            {{form.contact}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              联系电话
            </template>
            {{form.phoneNumber}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              创建者
            </template>
            {{form.createBy}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              创建时间
            </template>
            {{form.createTime}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              更新者
            </template>
            {{form.updateBy}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              更新时间
            </template>
            {{form.updateTime}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
             备注
            </template>
            {{form.remark}}
          </el-descriptions-item>





        </el-descriptions>
    </el-dialog>

  </div>
</template>

<script>
import { listStation, getStation, delStation, addStation, updateStation } from "@/api/charging/station";

export default {
  name: "Station",
  dicts: ['station_tag', 'is_or_not', 'charging_station_status', 'station_type', 'is_or_not'],
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
      // 充电站管理表格数据
      stationList: [],
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
        sysId: null,
        id: null,
        name: null,
        type: null,
        province: null,
        city: null,
        country: null,
        address: null,
        site: null,
        capacity: null,
        discount: null,
        couponStatus: null,
        parkingFreeStatus: null,
        tag: null,
        operator: null,
        stationStatus: null,
        regionNum: null,
        businessTimeStart: null,
        businessTimeEnd: null,
        contact: null,
        phoneNumber: null,
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
        id: [
          { required: true, message: "充电站ID不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "站点名称不能为空", trigger: "blur" }
        ],
        operator: [
          { required: true, message: "运营商名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询充电站管理列表 */
    getList() {
      this.loading = true;
      listStation(this.queryParams).then(response => {
        this.stationList = response.rows;
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
        id: null,
        name: null,
        type: null,
        province: null,
        city: null,
        country: null,
        address: null,
        site: null,
        capacity: null,
        longitude: null,
        latitude: null,
        discount: null,
        couponStatus: null,
        parkingFreeStatus: null,
        tag: null,
        operator: null,
        stationStatus: null,
        regionNum: null,
        businessCycle: null,
        businessTimeStart: null,
        businessTimeEnd: null,
        contact: null,
        phoneNumber: null,
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加充电站管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sysId = row.sysId || this.ids
      getStation(sysId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改充电站管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sysId != null) {
            updateStation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStation(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除充电站管理编号为"' + sysIds + '"的数据项？').then(function() {
        return delStation(sysIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 详细按钮操作 */
    handleView(row) {
      this.detailOpen = true;
      this.form = row;
    },
   
   /** 站点类型显示风格 danger   success   warning*/
    stationStyle(val){

        if(val == '0'){
            return "success"
        }else if(val == '1'){
            return ""
        }
    },


    /** 是否支持优惠券 显示风格 */
    iscouponStyle(val){

        if(val == '0'){
            return ""
        }else if(val == '1'){
            return "warning"
        }
    },
    /** 是否免费停车 显示风格 */
    isFreeStyle(val){

        if(val == '0'){
            return "success"
        }else if(val == '1'){
            return "danger"
        }
    },


    /** 导出按钮操作 */
    handleExport() {
      this.download('charging/station/export', {
        ...this.queryParams
      }, `station_${new Date().getTime()}.xlsx`)
    }
    
  }
};

</script>
