<!-- 导入bootstrap -->
import 'bootstrap/dist/css/bootstrap.css'

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="充电订单号" prop="chargingId">
        <el-input v-model="queryParams.chargingId" placeholder="请输入充电订单号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="支付订单号" prop="paymentId">
        <el-input v-model="queryParams.paymentId" placeholder="请输入支付订单号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="电桩编码" prop="pileId">
        <el-input v-model="queryParams.pileId" placeholder="请输入电桩编码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="电枪编码" prop="gunId">
        <el-input v-model="queryParams.gunId" placeholder="请输入电枪编码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="充电卡编码" prop="chargingCardId">
        <el-input v-model="queryParams.chargingCardId" placeholder="请输入充电卡编码" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="充电站名称" prop="stationName">
        <el-input v-model="queryParams.stationName" placeholder="请输入充电站名称" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="支付渠道" prop="paymentChannelTag">
        <el-select v-model="queryParams.paymentChannelTag" placeholder="请选择支付渠道" clearable>
          <el-option v-for="dict in dict.type.order_payment_channel" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="付款模式" prop="paymentMode">
        <el-select v-model="queryParams.paymentMode" placeholder="请选择付款模式" clearable>
          <el-option v-for="dict in dict.type.order_payment_mode" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="结束原因" prop="finishReason">
        <el-select v-model="queryParams.finishReason" placeholder="请选择结束原因" clearable>
          <el-option v-for="dict in dict.type.order_finish_reason" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input v-model="queryParams.userId" placeholder="请输入用户ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="车辆VIN" prop="vin">
        <el-input v-model="queryParams.vin" placeholder="请输入车辆VIN" clearable @keyup.enter.native="handleQuery" />
        <span slot="label">
          <el-tooltip content="车辆识别代码，以便识别车型和维护" placement="top">
            <i class="el-icon-question"></i>
          </el-tooltip>
          车辆VIN
        </span>
      </el-form-item>
      <el-form-item label="用户类型" prop="userType">
        <el-select v-model="queryParams.userType" placeholder="请选择用户类型" clearable>
          <el-option v-for="dict in dict.type.order_user_type" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['order:charging:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['order:charging:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['order:charging:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['order:charging:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="chargingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="充电订单号" align="center" prop="chargingId" width="180" />
      <el-table-column label="支付订单号" align="center" prop="paymentId" width="180" />
      <el-table-column label="电桩编码" align="center" prop="pileId" width="180" />
      <el-table-column label="电枪编码" align="center" prop="gunId" width="180" />
      <el-table-column label="充电卡编码" align="center" prop="chargingCardId" width="180" />
      <el-table-column label="用户ID" align="center" prop="userId" width="180" />
      <el-table-column label="车辆VIN" align="center" prop="vin" width="180" />
      <el-table-column label="充电站名称" align="center" prop="stationName" width="100" />
      <el-table-column label="充电状态" align="center" prop="chargingStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_charging_status" :value="scope.row.chargingStatus" />
        </template>
      </el-table-column>

      <el-table-column label="支付渠道" align="center" prop="paymentChannelTag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_payment_channel" :value="scope.row.paymentChannelTag" />
        </template>
      </el-table-column>

      <el-table-column label="付款模式" align="center" prop="paymentMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_payment_mode" :value="scope.row.paymentMode" />
        </template>
      </el-table-column>
      <el-table-column label="结束原因" align="center" prop="finishReason">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.order_finish_reason" :value="scope.row.finishReason" />
        </template>
      </el-table-column>
      <el-table-column label="充电量(度)" align="center" prop="chargingCapacity" />
      <el-table-column label="实付款(分)" align="center" prop="actualPayment" />
      <el-table-column label="备注" align="center" prop="remark" width="100" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <!-- <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['order:charging:edit']">修改</el-button> -->

          <el-button size="mini" type="text" icon="el-icon-document" @click="handleDrawer(scope.row)"
            v-hasPermi="['order:charging:detail']">详情</el-button>

          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['order:charging:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <el-drawer title="充电详细信息" :visible.sync="detailOpen" direction="ltr" size="80%">
      <el-divider content-position="left"><i class="el-icon-notebook-2"></i> 订单信息</el-divider>
      <!-- 订单信息 -->

      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">
        <el-descriptions-item>
          <template slot="label">
            <strong>系统ID</strong>
          </template>
          {{ form.sysId }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <strong>充电订单号</strong>
          </template>
          {{ form.chargingId }}
        </el-descriptions-item>


        <el-descriptions-item>
          <template slot="label">
            <strong>支付订单号</strong>
          </template>
          {{ form.paymentId }}
        </el-descriptions-item>


        <el-descriptions-item>
          <template slot="label">
            <strong>电桩编码</strong>
          </template>
          {{ form.pileId }}
        </el-descriptions-item>


        <el-descriptions-item>
          <template slot="label">
            <strong>电枪编码</strong>
          </template>
          {{ form.gunId }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <strong>充电卡编码</strong>
          </template>
          {{ form.chargingCardId }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <strong>充电站名称</strong>
          </template>
          {{ form.stationName }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>充电模式</strong>
          </template>
          <dict-tag :options="dict.type.order_charging_mode" :value="form.chargingMode" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>充电状态</strong>
          </template>
          <dict-tag :options="dict.type.order_charging_status" :value="form.chargingStatus" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>充电开始时间</strong>
          </template>
          <span>{{ parseTime(form.timeStart, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>充电结束时间</strong>
          </template>
          <span>{{ parseTime(form.timeEnd, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </el-descriptions-item>

      </el-descriptions>





      <el-divider content-position="left"><i class="el-icon-user"></i> 用户信息</el-divider>

      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">

        <!-- 用户信息 -->
        <el-descriptions-item>
          <template slot="label">
            <strong>用户ID</strong>
          </template>
          {{ form.userId }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>用户类型</strong>
          </template>
          <dict-tag :options="dict.type.order_user_type" :value="form.userType" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>车辆VIN</strong>
          </template>

        </el-descriptions-item>




      </el-descriptions>



      <!-- 优惠信息 -->
      <el-divider content-position="left"><i class="el-icon-discount"></i> 优惠信息</el-divider>
      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">
        <el-descriptions-item>
          <template slot="label">
            <strong>优惠券名称</strong>
          </template>
          {{ form.couponName }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>优惠券类型</strong>
          </template>
          <dict-tag :options="dict.type.order_coupon_type" :value="form.couponType" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>优惠额度</strong>
          </template>
          {{ form.couponQuota }}
        </el-descriptions-item>

      </el-descriptions>


      <!-- 支付信息 -->

      <el-divider content-position="left"><i class="el-icon-edit"></i> 支付信息</el-divider>
      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">

        <el-descriptions-item>
          <template slot="label">
            <strong>支付渠道</strong>
          </template>
          <dict-tag :options="dict.type.order_payment_channel" :value="form.paymentChannelTag" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>支付渠道(其他)</strong>
          </template>
          {{ form.paymentChannel }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>付款模式</strong>
          </template>
          <dict-tag :options="dict.type.order_payment_mode" :value="form.paymentMode" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>充电量(度)</strong>
          </template>
          {{ form.chargingCapacity }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>充电量(度)</strong>
          </template>
          {{ form.chargingCapacity }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>站点折扣</strong>
          </template>
          {{ form.stationDiscount }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>会员折扣开启</strong>
          </template>
          <dict-tag :options="dict.type.memberDiscountStatus" :value="form.memberDiscountStatus" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>会员折扣</strong>
          </template>
          {{ form.memberDiscount }}
        </el-descriptions-item>

      </el-descriptions>


      <!-- 支付金额 -->

      <el-divider content-position="left"><i class="el-icon-money"></i> 支付金额</el-divider>
      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">

        <el-descriptions-item>
          <template slot="label">
            <strong>预付款(分)</strong>
          </template>
          {{ form.advancePayment }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>应付款(分)</strong>
          </template>
          {{ form.duePayment }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>折扣金额(分)</strong>
          </template>
          {{ form.discountPayment }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>实付款(分)</strong>
          </template>
          {{ form.actualPayment }}
        </el-descriptions-item>

      </el-descriptions>


      <!-- 退款信息 -->
      <el-divider content-position="left"><i class="el-icon-document-delete"></i> 退款信息</el-divider>
      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">

        <el-descriptions-item>
          <template slot="label">
            <strong>退款表示</strong>
          </template>
          <dict-tag :options="dict.type.order_refund_tag" :value="form.refundTag" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>退款金额(分)</strong>
          </template>
          {{ form.refund }}
        </el-descriptions-item>

      </el-descriptions>



      <!-- 时段信息 -->
      <el-divider content-position="left"><i class="el-icon-time"></i> 时段信息</el-divider>
      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">

        <el-descriptions-item>
          <template slot="label">
            <strong>充电时段</strong>
          </template>
          <span>{{ parseTime(form.timeStart, '{m}-{d} {h}:{m}') }} - {{
            parseTime(form.timeEnd, '{m}-{d} {h}:{m}')
          }}</span>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>时段类型</strong>
          </template>
          <dict-tag :options="dict.type.order_charging_time_type" :value="form.timeType" />
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>电价单价(分)</strong>
          </template>
          {{ form.electricityPrice }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <strong>服务费单价(分)</strong>
          </template>
          {{ form.servePrice }}
        </el-descriptions-item>
      </el-descriptions>



      <!-- 其他信息 -->
      <el-divider content-position="left"><i class="el-icon-more-outline"></i> 其他</el-divider>
      <el-descriptions class="margin-top" :column="4" :size="size"
        :style="{ 'margin-left': '40px', 'margin-right': '5px' }">

        <el-descriptions-item>
          <template slot="label">
            <strong>订单创建时间</strong>
          </template>
          {{ form.createTime }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <strong>创建者</strong>
          </template>
          {{ form.createBy }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <strong>订单备注</strong>
          </template>
          {{ form.remark }}
        </el-descriptions-item>

      </el-descriptions>
    </el-drawer>


    <!-- 添加或修改充电订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">

        <!-- 订单信息 -->
        <el-divider content-position="left"><i class="el-icon-notebook-2"></i> 订单信息</el-divider>
        <el-row>
          <el-col span="6">
            <el-form-item label="充电订单号" prop="chargingId">
              <el-input v-model="form.chargingId" placeholder="请输入充电订单号" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="支付订单号" prop="paymentId">
              <el-input v-model="form.paymentId" placeholder="请输入支付订单号" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="电桩编码" prop="pileId">
              <el-input v-model="form.pileId" placeholder="请输入电桩编码" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="电枪编码" prop="gunId">
              <el-input v-model="form.gunId" placeholder="请输入电枪编码" />
            </el-form-item>
          </el-col>

        </el-row>

        <el-row>
          <el-col span="6">
            <el-form-item label="充电卡编码" prop="chargingCardId">
              <el-input v-model="form.chargingCardId" placeholder="请输入充电卡编码" />
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="充电站名称" prop="stationName">
              <el-input v-model="form.stationName" placeholder="请输入充电站名称" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="充电模式" prop="chargingMode">
              <el-select v-model="form.chargingMode" placeholder="请选择充电模式">
                <el-option v-for="dict in dict.type.order_charging_mode" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>


          <el-col span="6">
            <el-form-item label="充电状态" prop="chargingStatus">
              <el-select v-model="form.chargingStatus" placeholder="请选择充电状态">
                <el-option v-for="dict in dict.type.order_charging_status" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>


        <el-row>
          <el-col span="7">
            <el-form-item label="充电开始时间" prop="timeStart">
              <el-date-picker clearable v-model="form.timeStart" type="date" value-format="yyyy-MM-dd"
                placeholder="请选择充电开始时间">
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col span="7">
            <el-form-item label="充电结束时间" prop="timeEnd">
              <el-date-picker clearable v-model="form.timeEnd" type="date" value-format="yyyy-MM-dd"
                placeholder="请选择充电结束时间">
              </el-date-picker>
            </el-form-item>

          </el-col>
          <el-col span="7">
            <el-form-item label="结束原因" prop="finishReason">
              <el-select v-model="form.finishReason" placeholder="请选择结束原因">
                <el-option v-for="dict in dict.type.order_finish_reason" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>



        <!-- 用户信息 -->
        <el-divider content-position="left"><i class="el-icon-user"></i> 用户信息</el-divider>
        <el-row>
          <el-col span="6">
            <el-form-item label="用户ID" prop="userId">
              <el-input v-model="form.userId" placeholder="请输入用户ID" />
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="用户类型" prop="userType">
              <el-select v-model="form.userType" placeholder="请选择用户类型">
                <el-option v-for="dict in dict.type.order_user_type" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="车辆VIN" prop="vin">
              <el-input v-model="form.vin" placeholder="请输入车辆VIN" />
            </el-form-item>
          </el-col>

        </el-row>

        <!-- 优惠信息 -->
        <el-divider content-position="left"><i class="el-icon-discount"></i> 优惠信息</el-divider>

        <el-row>

          <el-col span="6">
            <el-form-item label="优惠券名称" prop="couponName">
              <el-input v-model="form.couponName" placeholder="请输入优惠券名称" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="优惠券类型" prop="couponType">
              <el-select v-model="form.couponType" placeholder="请选择优惠券类型">
                <el-option v-for="dict in dict.type.order_coupon_type" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="优惠额度" prop="couponQuota">
              <el-input v-model="form.couponQuota" placeholder="请输入优惠额度" />
            </el-form-item>
          </el-col>

        </el-row>
        <!-- 支付信息 -->
        <el-divider content-position="left"><i class="el-icon-edit"></i> 支付信息</el-divider>

        <el-row>
          <el-col span="6">
            <el-form-item label="支付渠道" prop="paymentChannelTag">
              <el-select v-model="form.paymentChannelTag" placeholder="请选择支付渠道">
                <el-option v-for="dict in dict.type.order_payment_channel" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>

          </el-col>
          <el-col span="12">
            <el-form-item label="支付渠道(其他)" prop="paymentChannel">
              <el-input v-model="form.paymentChannel" placeholder="请输入支付渠道" />
            </el-form-item>

          </el-col>

        </el-row>


        <el-row>
          <el-col span="6">
            <el-form-item label="付款模式" prop="paymentMode">
              <el-select v-model="form.paymentMode" placeholder="请选择付款模式">
                <el-option v-for="dict in dict.type.order_payment_mode" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="充电量(度)" prop="chargingCapacity">
              <el-input v-model="form.chargingCapacity" placeholder="请输入充电量(度)" />
            </el-form-item>
          </el-col>

        </el-row>


        <el-row>
          <el-col span="6">
            <el-form-item label="站点折扣" prop="stationDiscount">
              <el-input v-model="form.stationDiscount" placeholder="请输入站点折扣" />
            </el-form-item>

          </el-col>

          <el-col span="6">
            <el-form-item label="会员折扣" prop="memberDiscount">
              <el-input v-model="form.memberDiscount" placeholder="请输入会员折扣" />
            </el-form-item>

          </el-col>

          <el-col span="6">
            <el-form-item label="会员折扣开启" prop="memberDiscountStatus">
              <el-radio-group v-model="form.memberDiscountStatus">
                <el-radio v-for="dict in dict.type.is_or_not" :key="dict.value" :label="parseInt(dict.value)">{{
                  dict.label
                }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>




        <!-- 支付金额 -->
        <el-divider content-position="left"><i class="el-icon-money"></i> 支付金额</el-divider>

        <el-row>
          <el-col span="6">
            <el-form-item label="预付款(分)" prop="advancePayment">
              <el-input v-model="form.advancePayment" placeholder="请输入预付款(分)" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="应付款(分)" prop="duePayment">
              <el-input v-model="form.duePayment" placeholder="请输入应付款(分)" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="折扣金额(分)" prop="discountPayment">
              <el-input v-model="form.discountPayment" placeholder="请输入折扣金额(分)" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="实付款(分)" prop="actualPayment">
              <el-input v-model="form.actualPayment" placeholder="请输入实付款(分)" />
            </el-form-item>
          </el-col>
        </el-row>


        <!-- 退款信息 -->
        <el-divider content-position="left"><i class="el-icon-document-delete"></i> 退款信息</el-divider>

        <el-row>

          <el-col span="6">
            <el-form-item label="退款(分)" prop="refund">
              <el-input v-model="form.refund" placeholder="请输入退款(分)" />
            </el-form-item>
          </el-col>
          <el-col span="6">
            <el-form-item label="退款表示" prop="refundTag">
              <el-select v-model="form.refundTag" placeholder="请选择退款表示">
                <el-option v-for="dict in dict.type.order_refund_tag" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <!-- 时段信息 -->
        <el-divider content-position="left"><i class="el-icon-time"></i> 时段信息</el-divider>

        <el-row>
          <el-col span="6">
            <el-form-item label="时段类型" prop="timeType">
              <el-select v-model="form.timeType" placeholder="请选择时段类型">
                <el-option v-for="dict in dict.type.order_charging_time_type" :key="dict.value" :label="dict.label"
                  :value="parseInt(dict.value)"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="电价单价(分)" prop="electricityPrice">
              <el-input v-model="form.electricityPrice" placeholder="请输入电价单价(分)" />
            </el-form-item>
          </el-col>

          <el-col span="6">
            <el-form-item label="服务单价(分)" prop="servePrice">
              <el-input v-model="form.servePrice" placeholder="请输入服务单价(分)" />
            </el-form-item>
          </el-col>

        </el-row>



        <el-divider content-position="left"><i class="el-icon-more-outline"></i> 其他</el-divider>



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
import { listCharging, getCharging, delCharging, addCharging, updateCharging } from "@/api/order/charging";

export default {
  name: "Charging",
  dicts: ['order_charging_time_type', 'order_coupon_type', 'order_payment_channel', 'order_user_type', 'order_payment_mode', 'is_or_not', 'order_charging_mode', 'order_refund_tag', 'order_charging_status', 'order_finish_reason'],
  data() {
    return {

      //是否显示详情
      detailOpen: false,
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
      // 充电订单表格数据
      chargingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chargingId: null,
        paymentId: null,
        pileId: null,
        gunId: null,
        chargingCardId: null,
        stationName: null,
        paymentChannelTag: null,
        paymentMode: null,
        refundTag: null,
        finishReason: null,
        userId: null,
        vin: null,
        userType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        chargingId: [
          { required: true, message: "充电订单号不能为空", trigger: "blur" }
        ],
        paymentId: [
          { required: true, message: "支付订单号不能为空", trigger: "blur" }
        ],
        pileId: [
          { required: true, message: "电桩编码不能为空", trigger: "blur" }
        ],
        gunId: [
          { required: true, message: "电枪编码不能为空", trigger: "blur" }
        ],
        chargingCardId: [
          { required: true, message: "充电卡编码不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

    /**
        * 处理详情
        */
    handleDrawer(row) {
      this.detailOpen = true;
      this.form = row;
    },

    /** 查询充电订单列表 */
    getList() {
      this.loading = true;
      listCharging(this.queryParams).then(response => {
        this.chargingList = response.rows;
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
        chargingId: null,
        paymentId: null,
        pileId: null,
        gunId: null,
        chargingCardId: null,
        chargingMode: null,
        stationName: null,
        chargingStatus: null,
        timeStart: null,
        timeEnd: null,
        paymentChannelTag: null,
        paymentChannel: null,
        paymentMode: null,
        chargingCapacity: null,
        couponName: null,
        couponType: null,
        couponQuota: null,
        advancePayment: null,
        duePayment: null,
        discountPayment: null,
        actualPayment: null,
        refund: null,
        refundTag: null,
        finishReason: null,
        userId: null,
        vin: null,
        userType: null,
        timeType: null,
        electricityPrice: null,
        servePrice: null,
        stationDiscount: null,
        memberDiscount: null,
        memberDiscountStatus: null,
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
      this.title = "添加充电订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sysId = row.sysId || this.ids
      getCharging(sysId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改充电订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sysId != null) {
            updateCharging(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCharging(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除充电订单编号为"' + sysIds + '"的数据项？').then(function () {
        return delCharging(sysIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('order/charging/export', {
        ...this.queryParams
      }, `charging_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
